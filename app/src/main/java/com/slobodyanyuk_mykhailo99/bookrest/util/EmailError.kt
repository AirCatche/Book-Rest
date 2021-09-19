package com.slobodyanyuk_mykhailo99.bookrest.util

sealed class EmailError {
    object Empty : EmailError()
    object Format : EmailError()
    object Correct : EmailError()
}
