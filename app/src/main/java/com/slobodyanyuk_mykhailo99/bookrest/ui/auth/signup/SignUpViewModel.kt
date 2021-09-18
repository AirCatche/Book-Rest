package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.lifecycle.*
import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.SignUpRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.*
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login.LoginActivity
import com.slobodyanyuk_mykhailo99.bookrest.util.Coroutines
import com.slobodyanyuk_mykhailo99.bookrest.util.NetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.net.SocketTimeoutException
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel(), LifecycleObserver {

    lateinit var signUpListener: SignUpListener

    val email: MutableStateFlow<String> = MutableStateFlow("")
    val password: MutableStateFlow<String> = MutableStateFlow("")
    val confirmation: MutableStateFlow<String> = MutableStateFlow("")
    val username: MutableStateFlow<String> = MutableStateFlow("")

    val isValid: StateFlow<Boolean> = MutableStateFlow(false)

    fun onLoginText(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }
    }

    fun onSignUpButton() {
        signUpListener.onLoading()
        Coroutines.main {
            Log.d(TAG, "onSubmitClick: starts coroutine")
            try {
                val signUpResponse = repository
                    .userSignUp(SignUpRequest(
                        email.value,
                        password.value,
                        confirmation.value,
                        username.value,
                    ))
                Log.d(TAG, "onSignUp: user is ${signUpResponse.user}")
                signUpResponse.user?.let {
                    signUpListener.onSuccess(it)
                    return@main
                }
                signUpResponse.message?.let {
                    signUpListener.onFailure(it)
                }
            } catch(e: NetworkException) {
                when(e) {
                    is NetworkException.ApiException -> {signUpListener.onFailure(e.message!!)}
                    is NetworkException.NoInternetException -> {signUpListener.onFailure(e.message!!)}
                    is NetworkException.NoRespondException -> {signUpListener.onFailure(e.message!!)}
                }
            } catch (e: SocketTimeoutException) {
                signUpListener.onFailure(e.message!!)
            }
            Log.d(TAG, "onSignUp: coroutines end")
        }
    }

    val isUsernameCorrect: Flow<Boolean> = username.map {
        username.value.isNotBlank()
    }

    val isPasswordCorrect: Flow<Boolean> = password.map {
        PASSWORD_PATTERN.matcher(it).matches()
    }

    val isConfirmationCorrect: Flow<Boolean> = combine(password,confirmation) { password, confirmation ->
        val isPassCorrect = PASSWORD_PATTERN.matcher(password).matches()
        val isConfirmCorrect = password == confirmation
        return@combine isPassCorrect && isConfirmCorrect
    }

    val isEmailCorrect: Flow<Boolean> = email.map {
        Patterns.EMAIL_ADDRESS.matcher(it).matches()
    }

    val isSubmitEnabled: Flow<Boolean> = combine(
        isUsernameCorrect,
        isPasswordCorrect,
        isConfirmationCorrect,
        isEmailCorrect,
    ) { username, password, confirmation, email ->
        return@combine username && password && confirmation && email
    }

    fun setUsername(username: String){
        this.username.value = username
    }
    fun setPassword(password: String){
        this.password.value = password
    }
    fun setConfirmation(confirmation: String){
        this.confirmation.value = confirmation
    }
    fun setEmail(email: String){
        this.email.value = email
    }

    companion object {
        private const val TAG = "AuthViewModel"

        private val PASSWORD_PATTERN = Pattern.compile(
            "(?=.*[a-zA-Z])" +          // a-z A-Z
                    "(?=\\S+$)" +            //no white spaces
                    ".{6,15}" +              //at least 6 characters
                    "$"
        )
    }

}