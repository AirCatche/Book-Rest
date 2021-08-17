package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login

import android.content.Context
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
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository,
    private val preferenceProvider: PreferenceProvider,
    ) : ViewModel(), LifecycleObserver {

    lateinit var loginListener: LoginListener

    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    val usernameErrorMessage: MutableLiveData<String> = MutableLiveData()
    val passwordErrorMessage: MutableLiveData<String> = MutableLiveData()
    val responseError: MutableLiveData<String> = MutableLiveData()

    private var isUsernameValid: Boolean = false
    private var isPasswordValid: Boolean = false
    val isValid: MutableLiveData<Boolean> = MutableLiveData()

    fun onSignUpText(view: View) {
        Intent(view.context, SignUpActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }
    }

    fun onLoginButton(view: View) {     //??????
        loginListener.onStarted()
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

    companion object {
        private const val TAG = "AuthViewModel"
    }

}