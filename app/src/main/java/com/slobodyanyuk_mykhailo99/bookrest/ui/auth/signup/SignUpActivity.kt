package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.data.db.BookRestDatabase
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User
import com.slobodyanyuk_mykhailo99.bookrest.data.network.BookRestApi
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivitySignUpBinding
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.AuthListener
import com.slobodyanyuk_mykhailo99.bookrest.ui.home.HomeActivity

class SignUpActivity : AppCompatActivity(), AuthListener {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var loginViewModel: SignUpViewModel
    companion object {
        private const val TAG = "SignUpActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = BookRestApi()
        val db = BookRestDatabase(this)
        val repository = UserRepository(api, db)
        val factory = SignUpViewModelFactory(repository)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        loginViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.lifecycleOwner = this@SignUpActivity
        binding.viewmodel = loginViewModel
        loginViewModel.authListener = this

        loginViewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

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