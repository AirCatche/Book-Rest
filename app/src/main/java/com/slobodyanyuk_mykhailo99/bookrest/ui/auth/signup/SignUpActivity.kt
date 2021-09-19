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
import com.slobodyanyuk_mykhailo99.bookrest.util.*
import com.slobodyanyuk_mykhailo99.bookrest.util.Constants.SUCCESS
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
            .onEach { error ->
                binding.etSignupUsernameWrapper.error = when(error) {
                    UsernameError.Empty -> { getString(R.string.error_username_empty) }
                    UsernameError.Length -> { getString(R.string.error_username_length) }
                    UsernameError.Format -> { getString(R.string.error_username_incorrect) }
                    UsernameError.Correct -> { SUCCESS }
                }
            }.launchWhenStarted(lifecycleScope)

        viewModel.isPasswordCorrect
            .onEach { error ->
                binding.etSignupPasswordWrapper.error = when (error) {
                    PasswordError.Empty -> { getString(R.string.error_password_empty) }
                    PasswordError.Length -> { getString(R.string.error_password_length) }
                    PasswordError.Format -> { getString(R.string.error_password_incorrect) }
                    PasswordError.Correct -> { SUCCESS }
                }
            }.launchWhenStarted(lifecycleScope)

        viewModel.isConfirmationCorrect
            .onEach { error ->
                binding.etSignupConfirmationWrapper.error = when (error) {
                    ConfirmationError.Empty -> { getString(R.string.error_confirmation_empty) }
                    ConfirmationError.Format -> { getString(R.string.error_confirmation_incorrect) }
                    ConfirmationError.Different -> { getString(R.string.error_confirmation_different) }
                    ConfirmationError.Correct -> { SUCCESS }
                }
            }.launchWhenStarted(lifecycleScope)

        viewModel.isEmailCorrect
            .onEach { error ->
                binding.etSignupEmailWrapper.error = when (error) {
                    EmailError.Empty -> { getString(R.string.error_email_empty) }
                    EmailError.Format -> { getString(R.string.error_email_incorrect) }
                    EmailError.Correct -> { SUCCESS }
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