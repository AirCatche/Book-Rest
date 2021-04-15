package com.slobodyanyuk_mykhailo99.bookrest.ui.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onEmailFailure(message: String)
    fun onPasswordFailure(message: String)
    fun onConfirmationFailure(message: String)
}