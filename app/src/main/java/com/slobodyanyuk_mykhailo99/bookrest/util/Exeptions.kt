package com.slobodyanyuk_mykhailo99.bookrest.util

import java.io.IOException
import java.net.SocketTimeoutException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
//class NoResponseException(message: String) : SocketTimeoutException(message)