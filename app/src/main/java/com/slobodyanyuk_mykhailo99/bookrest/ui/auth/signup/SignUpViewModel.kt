package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.*

import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.SignUpRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.*
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login.LoginActivity
import com.slobodyanyuk_mykhailo99.bookrest.util.Coroutines
import com.slobodyanyuk_mykhailo99.bookrest.util.NetworkException
import java.net.SocketTimeoutException

class SignUpViewModel(private val repository: UserRepository) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmation = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val emailErrorMessage: MutableLiveData<String> = MutableLiveData()
    val passwordErrorMessage: MutableLiveData<String> = MutableLiveData()
    val confirmationErrorMessage: MutableLiveData<String> = MutableLiveData()
    val usernameErrorMessage: MutableLiveData<String> = MutableLiveData()
    val responseError = MutableLiveData<String>()
    val isValid: MutableLiveData<Boolean> = MutableLiveData()
    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    private var isConfirmationValid: Boolean = false
    private var isUsernameValid: Boolean = false
    var signUpListener: SignUpListener? = null

//    fun getLoggedInUser() = repository.getUser()

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

    fun onLoginText(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }
    }

    fun onSignUpButton(view: View) {
        signUpListener?.onStarted()
        Coroutines.main {
            Log.d(TAG, "onSubmitClick: starts coroutine")
            try {
                val signUpResponse = repository.userSignUp(SignUpRequest(email.value, password.value, confirmation.value, username.value))
                Log.d(TAG, "onSignUp: user is ${signUpResponse.user}")
                signUpResponse.user?.let {
                    signUpListener?.onSuccess(it)
//                    repository.saveUser(it)
                    return@main
                }
                Log.d(TAG, "onSignUp: response is ${signUpResponse.user?.username}")
                signUpResponse.message?.let {
                    signUpListener?.onFailure(it)
                }
            } catch(e: NetworkException) {
                when(e) {
                    is NetworkException.ApiException -> {signUpListener?.onFailure(e.message!!)}
                    is NetworkException.NoInternetException -> {signUpListener?.onFailure(e.message!!)}
                    is NetworkException.NoRespondException -> {signUpListener?.onFailure(e.message!!)}
                }
            }
            Log.d(TAG, "onSignUp: coroutines end")
        }
    }
}