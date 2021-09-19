package com.slobodyanyuk_mykhailo99.bookrest.util

sealed class UsernameError {
    object Empty : UsernameError()
    object Length : UsernameError()
    object Format : UsernameError()
    object Correct : UsernameError()
}