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
import com.slobodyanyuk_mykhailo99.bookrest.util.*
import com.slobodyanyuk_mykhailo99.bookrest.util.ValidationPattern.PASSWORD_PATTERN
import com.slobodyanyuk_mykhailo99.bookrest.util.ValidationPattern.USERNAME_PATTERN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.net.SocketTimeoutException
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

    val isUsernameCorrect: Flow<UsernameError> = username.map {
        val lengthNotMatch = it.length < 3 || it.length > 15
        val incorrectUsername = !USERNAME_PATTERN.matcher(it).matches()
        when {
            it.isBlank() -> { UsernameError.Empty }
            lengthNotMatch -> { UsernameError.Length }
            incorrectUsername -> { UsernameError.Format }
            else -> { UsernameError.Correct }
        }
    }

    val isPasswordCorrect: Flow<PasswordError> = password.map {
        val incorrectPassword = !PASSWORD_PATTERN.matcher(it).matches()
        val lengthNotMatch = it.length < 6 || it.length > 15
        when {
            it.isBlank() -> { PasswordError.Empty }
            lengthNotMatch -> { PasswordError.Length }
            incorrectPassword -> { PasswordError.Format }
            else -> { PasswordError.Correct }
        }
    }

    val isConfirmationCorrect: Flow<ConfirmationError> = combine(password,confirmation) { password, confirmation ->
        val incorrectConfirmation = !PASSWORD_PATTERN.matcher(confirmation).matches()
        val isDifferent = confirmation != password
        return@combine when {
            confirmation.isBlank() -> { ConfirmationError.Empty }
            incorrectConfirmation -> { ConfirmationError.Format }
            isDifferent -> { ConfirmationError.Different }
            else -> { ConfirmationError.Correct }
        }
    }

    val isEmailCorrect: Flow<EmailError> = email.map {
        val emailIncorrect = !Patterns.EMAIL_ADDRESS.matcher(it).matches()
        when {
            it.isBlank() -> { EmailError.Empty }
            emailIncorrect -> { EmailError.Format }
            else -> { EmailError.Correct }
        }
    }

    val isSubmitEnabled: Flow<Boolean> = combine(
        username,
        password,
        confirmation,
        email,
    ) { username, password, confirmation, email ->
        return@combine USERNAME_PATTERN.matcher(username).matches() &&
                        PASSWORD_PATTERN.matcher(password).matches() &&
                        confirmation == password &&
                        Patterns.EMAIL_ADDRESS.matcher(email).matches()
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

    }
}