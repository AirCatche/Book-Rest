package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivityLoginBinding
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.HomeActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), LoginListener, KodeinAware {

    override val kodein by kodein()
    private val factory: LoginViewModelFactory by instance()

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    companion object {
        private const val TAG = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.lifecycleOwner = this@LoginActivity
        binding.viewmodel = loginViewModel
        loginViewModel.loginListener = this

    }
    override fun onStart() {
        super.onStart()
        loginViewModel.setupInputObservers(this, this)
    }
    override fun onStarted() {
        Log.d(TAG, "onStarted: starts")
    }

    override fun onSuccess(token: String) {
        Log.d(TAG, "onSuccess: starts")
        Log.d(TAG, "onSuccess: token is: $token")
        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        Log.d(TAG, "onFailure: starts with message ---> $message")
        loginViewModel.responseError.postValue(message)
    }

}