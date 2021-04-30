package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.LoginRequest

import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.*
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup.SignUpActivity
import com.slobodyanyuk_mykhailo99.bookrest.util.ApiException
import com.slobodyanyuk_mykhailo99.bookrest.util.Coroutines
import com.slobodyanyuk_mykhailo99.bookrest.util.NoInternetException

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val usernameErrorMessage: MutableLiveData<String> = MutableLiveData()
    val passwordErrorMessage: MutableLiveData<String> = MutableLiveData()


    val isValid:MutableLiveData<Boolean> = MutableLiveData()
//    var isCorrectPicture:MutableLiveData<Boolean> = MutableLiveData()
//    private var isEmailValid: Boolean = false
        private var isUsernameValid: Boolean = false
        private var isPasswordValid: Boolean = false
//    private var isConfirmationValid: Boolean = false
    var loginListener: LoginListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun setupInputObservers (lifecycleOwner: LifecycleOwner, context: Context) {
        username.observe(lifecycleOwner, Observer {
            val validationModel = it.validateUsername(context)
            isUsernameValid = validationModel.isValid
            validateInput(isUsernameValid, isPasswordValid)
            usernameErrorMessage.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, Observer {
            val validationModel = it.validatePassword(context)
            isPasswordValid = validationModel.isValid
            validateInput(isUsernameValid, isPasswordValid)
            passwordErrorMessage.postValue(validationModel.message)
        })
    }

    private fun validateInput(username:Boolean, password:Boolean) {
        isValid.postValue(password&&username)
    }

    fun onSignUpText(view: View) {
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLoginButton(view: View) {
        loginListener?.onStarted()
        Log.d(TAG, "onSubmitClick: ${username.value}")
        Log.d(TAG, "onSubmitClick: ${password.value}")
        Coroutines.main {
            Log.d(TAG, "onSubmitClick: starts coroutine")
            try {
                val loginResponse = repository.userLogin(LoginRequest(username.value ,password.value ))
                Log.d(TAG, "onLogin: user is ${loginResponse.username}")
                loginResponse.token?.let {
                    loginListener?.onSuccess(it)
                    return@main
                }
                Log.d(TAG, "onSignUp: response is ${loginResponse.token}")
                loginResponse.message?.let {
                    loginListener?.onFailure(it)
                }
            } catch (e: ApiException) {
                loginListener?.onFailure(e.message!!)
            } catch (e:NoInternetException) {
                loginListener?.onFailure(e.message!!)
            }
            Log.d(TAG, "onLogin: coroutines end")
        }
    }
}