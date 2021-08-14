 package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivityLoginBinding
import com.slobodyanyuk_mykhailo99.bookrest.ui.DialogLoading
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint
 class LoginActivity : AppCompatActivity(), LoginListener {

    private lateinit var dialogLoading: DialogLoading
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        viewModel.loginListener = this
        dialogLoading = DialogLoading(this)

    }
    override fun onStart() {
        super.onStart()
        viewModel.setupInputObservers(this, this)
    }
    override fun onStarted() {
        Log.d(TAG, "onStarted: starts")
        dialogLoading.startLoadingDialog()
    }

    override fun onSuccess(token: String) {
        Log.d(TAG, "onSuccess: starts")
        dialogLoading.dismissDialog()
        Log.d(TAG, "onSuccess: token is: $token")

        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        Log.d(TAG, "onFailure: starts with message ---> $message")
        dialogLoading.dismissDialog()
        viewModel.responseError.postValue(message)
    }

    companion object {
        private const val TAG = "LoginActivity"
    }

}