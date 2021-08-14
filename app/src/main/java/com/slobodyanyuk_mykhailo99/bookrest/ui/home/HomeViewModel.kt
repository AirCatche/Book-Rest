package com.slobodyanyuk_mykhailo99.bookrest.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel(), LifecycleObserver {

}