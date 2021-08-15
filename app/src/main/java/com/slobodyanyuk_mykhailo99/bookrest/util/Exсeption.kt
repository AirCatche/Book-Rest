package com.slobodyanyuk_mykhailo99.bookrest.util

import java.io.IOException


sealed class NetworkResult(message: String): IOException(message) {
    class NoInternetException(message : String) : NetworkResult(message)
    class ApiException(message: String) : NetworkResult(message)
    class NoRespondException (message: String): NetworkResult(message)
}

