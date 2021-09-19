package com.slobodyanyuk_mykhailo99.bookrest.util

sealed class ConfirmationError {
    object Empty : ConfirmationError()
    object Format : ConfirmationError()
    object Different : ConfirmationError()
    object Correct : ConfirmationError()

}
