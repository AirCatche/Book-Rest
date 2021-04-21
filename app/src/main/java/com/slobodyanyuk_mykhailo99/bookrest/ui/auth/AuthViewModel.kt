package com.slobodyanyuk_mykhailo99.bookrest.ui.auth

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.SignUpData
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.SignUpResponse
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.util.Coroutines

import retrofit2.Response

class AuthViewModel : ViewModel() {

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
    val isValid:MutableLiveData<Boolean> = MutableLiveData()
    var isCorrectPicture:MutableLiveData<Boolean> = MutableLiveData()


    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    private var isConfirmationValid: Boolean = false
    private var isUsernameValid: Boolean = false

    var authListener: AuthListener? = null

    fun setupInputObservers (lifecycleOwner: LifecycleOwner, context: Context) {
        email.observe(lifecycleOwner, Observer {
            val validationModel = it.validateEmail(context)
            isEmailValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isConfirmationValid, isUsernameValid)
            emailErrorMessage.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, Observer {
            val validationModel = it.validatePassword(context, confirmation.value)
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

    fun onSignUp(view: View) {
        authListener?.onStarted()
        Log.d(TAG, "onSubmitClick: ${email.value}")
        Log.d(TAG, "onSubmitClick: ${password.value}")
        Log.d(TAG, "onSubmitClick: ${confirmation.value}")
        Log.d(TAG, "onSubmitClick: ${username.value}")

        //STRONG DEPENDENCY MUST BE REWRITE
        Coroutines.main {
            Log.d(TAG, "onSubmitClick: starts coroutine")
            val signUpData = SignUpData(email.value, password.value, confirmation.value, username.value)
            val response: Response<SignUpResponse> = UserRepository().userSignUp(signUpData)
            Log.d(TAG, "onSubmitClick: ${response.isSuccessful} is response")
            if (response.isSuccessful) {
                authListener?.onSuccess(response.body()?.user!!)
            } else {
                authListener?.onFailure("Error code: ${response.code()}")
            }
            Log.d(TAG, "onSignUp: coroutines end")
        }
    }
}