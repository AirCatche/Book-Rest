package com.slobodyanyuk_mykhailo99.bookrest.ui.auth

import android.app.Application
import android.content.Context
import androidx.lifecycle.*

import java.util.regex.Pattern

class AuthViewModel : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var confirmation = MutableLiveData<String>()
    var emailErrorMessage: MutableLiveData<String> = MutableLiveData()
    var passwordErrorMessage: MutableLiveData<String> = MutableLiveData()
    var confirmationErrorMessage: MutableLiveData<String> = MutableLiveData()

    var isValid:MutableLiveData<Boolean> = MutableLiveData()
    var isCorrectPicture:MutableLiveData<Boolean> = MutableLiveData()


    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    private var isConfirmationValid: Boolean = false

    var authListener: AuthListener? = null

    fun setupInputObservers (lifecycleOwner: LifecycleOwner, context: Context) {
        email.observe(lifecycleOwner, Observer {
            val validationModel = it.validateEmail(context)
            isEmailValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isConfirmationValid)
            emailErrorMessage.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, Observer {
            val validationModel = it.validatePassword(context, confirmation.value)
            isPasswordValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isConfirmationValid)
            passwordErrorMessage.postValue(validationModel.message)
        })
        confirmation.observe(lifecycleOwner, Observer {
            val validationModel = it.validateConfirmation(password.value, context)
            isConfirmationValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isConfirmationValid)
            confirmationErrorMessage.postValue(validationModel.message)
        })
    }

    private fun validateInput(email:Boolean,password:Boolean,confirmation:Boolean) {
        isValid.postValue(email&&password&&confirmation)

    }
//    fun onSubmitClick(view: View) {
//        authListener?.onStarted()
//        Log.d(TAG, "onSubmitClick: $email")
//        Log.d(TAG, "onSubmitClick: $password")
//        Log.d(TAG, "onSubmitClick: $confirmation")
//        if (ValidationModel.isBlank(email.value)) {
//            authListener?.onEmailFailure(FAILURE_EMAIL_EMPTY)
//        } else if (ValidationModel.isIncorrectEmail(email.value)) {
//            authListener?.onEmailFailure(FAILURE_EMAIL_INCORRECT)
//        }
//        if (ValidationModel.isBlank(password.value)) {
//            authListener?.onPasswordFailure(FAILURE_PASSWORD_EMPTY)
//        } else if (ValidationModel.isIncorrectPassword(password.value)) {
//            authListener?.onPasswordFailure(FAILURE_PASSWORD_INCORRECT)
//        }
//        if (ValidationModel.isBlank(confirmation.value)) {
//            authListener?.onConfirmationFailure(FAILURE_CONFIRMATION_EMPTY)
//        } else if (ValidationModel.isIncorrectConfirmation(password.value, confirmation.value)) {
//            authListener?.onConfirmationFailure(FAILURE_CONFIRMATION_INCORRECT)
//        }
//        if (ValidationModel.valid(email.value,password.value,confirmation.value)) {
//            authListener?.onSuccess()
//            return
//        }
//    }
}