package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.preference.PreferenceProvider

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val repository: UserRepository, private val preferences: PreferenceProvider) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository, preferences) as T
    }
}