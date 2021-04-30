package com.slobodyanyuk_mykhailo99.bookrest.data.network.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest (@SerializedName("username") val username: String? = null,
                         @SerializedName("password") val password: String? = null)
