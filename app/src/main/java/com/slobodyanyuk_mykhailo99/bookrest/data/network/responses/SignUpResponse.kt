package com.slobodyanyuk_mykhailo99.bookrest.data.network.responses

import com.google.gson.annotations.SerializedName
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User

data class SignUpResponse (@SerializedName("registeredUser")val user: User?, @SerializedName("errorMessage")val message: String?)
