package com.slobodyanyuk_mykhailo99.bookrest.ui.home

import androidx.lifecycle.ViewModel
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.preference.PreferenceProvider
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: UserRepository, private val preferenceProvider: PreferenceProvider) : ViewModel() {

}