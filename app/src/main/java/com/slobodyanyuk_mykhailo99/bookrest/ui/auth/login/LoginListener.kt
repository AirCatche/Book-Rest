package com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login

interface LoginListener {
    fun onLoading()
    fun onSuccess(token: String)
    fun onFailure(message: String)
}