package com.slobodyanyuk_mykhailo99.bookrest.data.network

import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.LoginRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.SignUpRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.LoginResponse
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.SignUpResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BookRestApi {

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun userSignUp(@Body request: SignUpRequest) : Response<SignUpResponse>

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun userLogin(@Body request: LoginRequest) : Response<LoginResponse>

}