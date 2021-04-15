package com.slobodyanyuk_mykhailo99.bookrest.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), AuthListener {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: AuthViewModel
    companion object {
        private const val TAG = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.lifecycleOwner = this@LoginActivity
        binding.viewmodel = loginViewModel
        loginViewModel.authListener = this

    }
    override fun onStart() {
        super.onStart()
        loginViewModel.setupInputObservers(this, this)
    }

    override fun onStarted() {
        Log.d(TAG, "onStarted: starts")
    }

    override fun onSuccess() {
        Log.d(TAG, "onSuccess: starts")
    }

    override fun onEmailFailure(message: String) {
        Log.d(TAG, "onEmailFailure: called $message")
    }

    override fun onPasswordFailure(message: String) {
        Log.d(TAG, "onPasswordFailure: called $message")
    }

    override fun onConfirmationFailure(message: String) {
        Log.d(TAG, "onConfirmationFailure: called $message")

    }

}