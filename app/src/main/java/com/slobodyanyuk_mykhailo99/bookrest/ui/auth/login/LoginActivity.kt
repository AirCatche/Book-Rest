 package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivityLoginBinding
import com.slobodyanyuk_mykhailo99.bookrest.ui.helpers.DialogLoading
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.HomeActivity

import com.slobodyanyuk_mykhailo99.bookrest.util.Constants.SUCCESS
import com.slobodyanyuk_mykhailo99.bookrest.util.launchWhenStarted
import com.slobodyanyuk_mykhailo99.bookrest.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

 @AndroidEntryPoint
 class LoginActivity : AppCompatActivity(), LoginListener {

    private lateinit var dialogLoading: DialogLoading
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        viewModel.loginListener = this
        dialogLoading = DialogLoading(this)

    }
    override fun onStart() {
        super.onStart()
        initObservers()
        initListeners()
    }
    override fun onLoading() {
        Log.d(TAG, "onStarted: starts")
        dialogLoading.startLoadingDialog()
    }

    override fun onSuccess(token: String) {
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
        binding.tvLoginResponseError.text = message
        binding.tvLoginResponseError.visible()

    }

     private fun initObservers() {
         viewModel.isSubmitEnabled
             .onEach { value ->
                 binding.btnLoginSubmit.isEnabled = value
             }.launchWhenStarted(lifecycleScope)

         viewModel.isUsernameCorrect
             .onEach { isCorrect ->
                 binding.etLoginUsernameWrapper.error = if (isCorrect) {
                     SUCCESS
                 } else {
                     getString(R.string.error_empty_username)
                 }
             }.launchWhenStarted(lifecycleScope)

         viewModel.isPasswordCorrect
             .onEach { isCorrect ->
                 binding.etLoginPasswordWrapper.error = if (isCorrect) {
                     SUCCESS
                 } else{
                     getString(R.string.error_incorrect_password)
                 }
             }.launchWhenStarted(lifecycleScope)
     }

     private fun initListeners() {

         with(binding) {
             //Buttons
             btnLoginSubmit.setOnClickListener {
                 viewModel.login()
             }

             tvSignupButton.setOnClickListener {
                 viewModel.onSignUpText(it)
             }

             //Fields
             etLoginUsername.addTextChangedListener {
                 viewModel.setUsername(it.toString())
             }
             etLoginPassword.addTextChangedListener {
                 viewModel.setPassword(it.toString())
             }

         }
     }

    companion object {
        private const val TAG = "LoginActivity"
    }

}