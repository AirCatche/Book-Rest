package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivitySignUpBinding
import com.slobodyanyuk_mykhailo99.bookrest.ui.helpers.DialogLoading
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login.LoginActivity
import com.slobodyanyuk_mykhailo99.bookrest.util.Constants.SUCCESS
import com.slobodyanyuk_mykhailo99.bookrest.util.launchWhenStarted
import com.slobodyanyuk_mykhailo99.bookrest.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity(), SignUpListener {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var dialogLoading: DialogLoading
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this@SignUpActivity
        viewModel.signUpListener = this
        dialogLoading = DialogLoading(this)

//        signUpViewModel.getLoggedInUser().observe(this, Observer { user ->
//            if (user != null) {
//                Intent(this, LoginActivity::class.java).also {
//                    startActivity(it)
//                }
//            }
//        })

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

    override fun onSuccess(user: User) {
        Log.d(TAG, "onSuccess: starts")
        Log.d(TAG, "onSuccess: ${user.username} sign up now")
        Intent(this, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        Log.d(TAG, "onFailure: starts with message ---> $message")
        dialogLoading.dismissDialog()
        binding.tvSignupResponseError.text = message
        binding.tvSignupResponseError.visible()
    }

    private fun initObservers() {
        viewModel.isSubmitEnabled
            .onEach { value ->
                binding.btnSignupSubmit.isEnabled = value
            }.launchWhenStarted(lifecycleScope)

        viewModel.isUsernameCorrect
            .onEach { isCorrect ->
                binding.etSignupUsernameWrapper.error = if (isCorrect) {
                    SUCCESS
                } else {
                    getString(R.string.error_empty_username)
                }
            }.launchWhenStarted(lifecycleScope)

        viewModel.isPasswordCorrect
            .onEach { isCorrect ->
                binding.etSignupPasswordWrapper.error = if (isCorrect) {
                    SUCCESS
                } else{
                    getString(R.string.error_incorrect_password)
                }
            }.launchWhenStarted(lifecycleScope)

        viewModel.isConfirmationCorrect
            .onEach { isCorrect ->
                binding.etSignupConfirmationWrapper.error = if (isCorrect) {
                    SUCCESS
                } else {
                    getString(R.string.error_incorrect_confirmation)
                }
            }.launchWhenStarted(lifecycleScope)

        viewModel.isEmailCorrect
            .onEach { isCorrect ->
                binding.etSignupEmailWrapper.error = if (isCorrect) {
                    SUCCESS
                } else{
                    getString(R.string.error_incorrect_email)
                }
            }.launchWhenStarted(lifecycleScope)
    }

    private fun initListeners() {
        with(binding) {
            //BUTTONS
            btnSignupSubmit.setOnClickListener {
                viewModel.onSignUpButton()
            }
            tvLoginButton.setOnClickListener {
                viewModel.onLoginText(it)
            }
            // FIELDS
            etSignupUsername.addTextChangedListener {
                viewModel.setUsername(it.toString())
            }
            etSignupPassword.addTextChangedListener {
                viewModel.setPassword(it.toString())
            }
            etSignupConfirmation.addTextChangedListener {
                viewModel.setConfirmation(it.toString())
            }
            etSignupEmail.addTextChangedListener {
                viewModel.setEmail(it.toString())
            }
        }
    }

    companion object {
        private const val TAG = "SignUpActivity"
    }
}