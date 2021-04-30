package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.SignUpResponse

interface SignUpListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}