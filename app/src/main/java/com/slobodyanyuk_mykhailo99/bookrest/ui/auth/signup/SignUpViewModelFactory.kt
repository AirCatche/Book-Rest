package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpViewModel(repository) as T
    }
}