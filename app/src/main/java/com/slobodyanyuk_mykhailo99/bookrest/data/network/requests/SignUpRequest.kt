package com.slobodyanyuk_mykhailo99.bookrest.data.network.requests

import com.google.gson.annotations.SerializedName

data class SignUpRequest(@SerializedName("email") val email: String? = null,
                         @SerializedName("password") val password: String? = null,
                         @SerializedName("matchingPassword") val confirmation: String? = null,
                         @SerializedName("username") val username: String? = null)