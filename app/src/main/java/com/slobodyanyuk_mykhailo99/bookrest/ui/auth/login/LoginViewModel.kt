package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.LoginRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.preference.PreferenceProvider
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.*
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup.SignUpActivity
import com.slobodyanyuk_mykhailo99.bookrest.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.net.SocketTimeoutException
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository,
    private val preferenceProvider: PreferenceProvider,
    ) : ViewModel(), LifecycleObserver {

    lateinit var loginListener: LoginListener

    private val _username = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val username: StateFlow<String> = _username.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isValid = MutableStateFlow(false)

    fun onSignUpText(view: View) {
        Intent(view.context, SignUpActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }
    }

    fun login() {
        loginListener.onLoading()
        Log.d(TAG, "onSubmitClick: ${username.value}")
        Log.d(TAG, "onSubmitClick: ${password.value}")
        Coroutines.main {
            Log.d(TAG, "onSubmitClick: starts coroutine")
            try {
                val loginResponse = repository.userLogin(LoginRequest(username.value ,password.value))
                Log.d(TAG, "onLogin: STATUS is ${loginResponse.verificationStatus}")
                loginResponse.token?.let {
                    loginListener.onSuccess(it)
                    preferenceProvider.saveToken(it)
                    return@main
                }
                loginResponse.message?.let {
                    loginListener.onFailure(it)
                }
            } catch(e: NetworkException) {
                when(e) {

                    is NetworkException.ApiException -> {loginListener.onFailure(e.message!!)}

                    is NetworkException.NoInternetException -> {loginListener.onFailure(e.message!!)}

                    is NetworkException.NoRespondException -> {loginListener.onFailure(e.message!!)}

                }
            } catch (e: SocketTimeoutException) {
                loginListener.onFailure(e.message!!)
            }
            Log.d(TAG, "onLogin: coroutines end")
        }
    }

    val isUsernameCorrect: Flow<UsernameError> = username.map {
        when {
            it.isBlank() -> { UsernameError.Empty }
            it.length < 3 || it.length > 15 -> { UsernameError.Length }
            !USERNAME_PATTERN.matcher(it).matches() -> { UsernameError.Format }
            else -> { UsernameError.Correct }
        }
    }

    val isPasswordCorrect: Flow<PasswordError> = password.map {
        when {
            it.isBlank() -> { PasswordError.Empty }
            it.length < 6 || it.length > 15 -> { PasswordError.Length }
            !PASSWORD_PATTERN.matcher(it).matches() -> { PasswordError.Format }
            else -> { PasswordError.Correct }
        }
    }

    val isSubmitEnabled: Flow<Boolean> = combine(username, password) { username, password ->
        return@combine USERNAME_PATTERN.matcher(username).matches() && PASSWORD_PATTERN.matcher(password).matches()
    }

    fun setUsername(username: String){
        _username.value = username
    }
    fun setPassword(password: String){
        _password.value = password
    }
    companion object {
        private const val TAG = "AuthViewModel"
        private val PASSWORD_PATTERN = Pattern.compile(
            "(?=.*[a-zA-Z])" +          // a-z A-Z
                    "(?=\\S+$)" +            //no white spaces
                    ".{6,15}" +              //at least 6 characters
                    "$"
        )
        private val USERNAME_PATTERN = Pattern.compile(
            "(?=.*[a-zA-Z])" +          // a-z A-Z
                    ".{3,15}" +              //at least 3 characters
                    "$"
        )

    }

}
