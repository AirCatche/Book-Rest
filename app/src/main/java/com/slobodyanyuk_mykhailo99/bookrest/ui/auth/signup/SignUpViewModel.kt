package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.*

import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.SignUpRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.preference.PreferenceProvider
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.*
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login.LoginActivity
import com.slobodyanyuk_mykhailo99.bookrest.util.Coroutines
import com.slobodyanyuk_mykhailo99.bookrest.util.NetworkException
import java.net.SocketTimeoutException

class SignUpViewModel(private val repository: UserRepository) : ViewModel() {

    lateinit var preferenceProvider: PreferenceProvider
    lateinit var signUpListener: SignUpListener

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val confirmation: MutableLiveData<String> = MutableLiveData()
    val username: MutableLiveData<String> = MutableLiveData()

    val emailErrorMessage: MutableLiveData<String> = MutableLiveData()
    val passwordErrorMessage: MutableLiveData<String> = MutableLiveData()
    val confirmationErrorMessage: MutableLiveData<String> = MutableLiveData()
    val usernameErrorMessage: MutableLiveData<String> = MutableLiveData()
    val responseErrorMessage: MutableLiveData<String> = MutableLiveData()

    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    private var isConfirmationValid: Boolean = false
    private var isUsernameValid: Boolean = false
    val isValid: MutableLiveData<Boolean> = MutableLiveData()

    fun onLoginText(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }
    }

    fun onSignUpButton(view: View) {
        signUpListener.onStarted()
        Coroutines.main {
            Log.d(TAG, "onSubmitClick: starts coroutine")
            try {
                val signUpResponse = repository
                    .userSignUp(SignUpRequest(
                        email.value,
                        password.value,
                        confirmation.value,
                        username.value))
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

    fun setupInputObservers (lifecycleOwner: LifecycleOwner, context: Context) {
        email.observe(lifecycleOwner, Observer {
            val validationModel = it.validateEmail(context)
            isEmailValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isConfirmationValid, isUsernameValid)
            emailErrorMessage.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, Observer {
            val validationModel = it.validatePassword(context)
            isPasswordValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isConfirmationValid, isUsernameValid)
            passwordErrorMessage.postValue(validationModel.message)
        })
        confirmation.observe(lifecycleOwner, Observer {
            val validationModel = it.validateConfirmation(password.value, context)
            isConfirmationValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isConfirmationValid, isUsernameValid)
            confirmationErrorMessage.postValue(validationModel.message)
        })
        username.observe(lifecycleOwner, Observer {
            val validationModel = it.validateUsername(context)
            isUsernameValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isConfirmationValid, isUsernameValid)
            usernameErrorMessage.postValue(validationModel.message)
        })
    }

    private fun validateInput(email:Boolean,password:Boolean,confirmation:Boolean,username:Boolean) {
        isValid.postValue(email&&password&&confirmation&&username)
    }

    companion object {
        private const val TAG = "AuthViewModel"
    }

}