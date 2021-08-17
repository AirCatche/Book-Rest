package com.slobodyanyuk_mykhailo99.bookrest.util

import java.io.IOException


sealed class NetworkException(message: String): IOException(message) {
    class NoInternetException(message : String) : NetworkException(message)
    class ApiException(message: String) : NetworkException(message)
    class NoRespondException (message: String): NetworkException(message)
}

