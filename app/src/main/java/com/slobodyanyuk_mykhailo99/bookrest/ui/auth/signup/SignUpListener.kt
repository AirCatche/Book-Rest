package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup

import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User

interface SignUpListener {
    fun onLoading()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}