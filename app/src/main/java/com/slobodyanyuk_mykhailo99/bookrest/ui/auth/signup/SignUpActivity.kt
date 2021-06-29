package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo99.bookrest.R
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User
import com.slobodyanyuk_mykhailo99.bookrest.databinding.ActivitySignUpBinding
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), SignUpListener, KodeinAware {

    override val kodein by kodein()
    private val factory: SignUpViewModelFactory by instance()

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel
    companion object {
        private const val TAG = "SignUpActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel = ViewModelProvider(this, factory).get(SignUpViewModel::class.java)
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
        Intent(this, SignUpActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        Log.d(TAG, "onFailure: starts with message ---> $message")
        signUpViewModel.responseErrorMessage.value = message
    }

}