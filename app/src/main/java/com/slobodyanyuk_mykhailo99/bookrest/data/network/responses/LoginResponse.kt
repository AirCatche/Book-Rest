package com.slobodyanyuk_mykhailo99.bookrest.data.network.responses

import com.google.gson.annotations.SerializedName
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Role
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User

data class LoginResponse (@SerializedName("roles")val roles: List<Role>?,
                          @SerializedName("token")val token: String?,
                          @SerializedName("username")val username: String?,
                          @SerializedName("errorMessage")val message: String?,
                          @SerializedName("verificationStatus") val verificationStatus: String?)
                        //  @SerializedName("validationErrors")val validationErrors: ValidationErrors?)
