package com.slobodyanyuk_mykhailo99.bookrest.ui.auth

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), AuthListener {
    lateinit var binding: ActivitySignUpBinding
    lateinit var loginViewModel: AuthViewModel
    companion object {
        private const val TAG = "SignUpActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        loginViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.lifecycleOwner = this@SignUpActivity
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

    override fun onSuccess(user: User) {
        Log.d(TAG, "onSuccess: starts")

        Log.d(TAG, "onSuccess: ${user.username} sign up now")
    }

    override fun onFailure(message: String) {
        Log.d(TAG, "onFailure: starts with message ---> $message")
    }

}