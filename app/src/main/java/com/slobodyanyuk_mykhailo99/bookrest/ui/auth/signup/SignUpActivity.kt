package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivitySignUpBinding
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity(), SignUpListener {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this@SignUpActivity
        binding.viewmodel = signUpViewModel
        signUpViewModel.signUpListener = this

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
        signUpViewModel.setupInputObservers(this, this)
    }
    override fun onStarted() {
        Log.d(TAG, "onStarted: starts")
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
        signUpViewModel.responseErrorMessage.value = message
    }

    companion object {
        private const val TAG = "SignUpActivity"
    }
}