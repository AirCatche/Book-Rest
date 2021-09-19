package com.slobodyanyuk_mykhailo99.bookrest.util

sealed class PasswordError {
    object Empty : PasswordError()
    object Length : PasswordError()
    object Format : PasswordError()
    object Correct : PasswordError()

}
