package com.slobodyanyuk_mykhailo99.bookrest.data.network

import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.LoginRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.SignUpRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.LoginResponse
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.SignUpResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BookRestApi {

    companion object {
        operator fun invoke() : BookRestApi {
            return Retrofit.Builder()
                .baseUrl("https://bookrest1.herokuapp.com/api/v1/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookRestApi::class.java)
        }
    }

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun userSignUp(@Body requestData: SignUpRequest) : Response<SignUpResponse>

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun userLogin(@Body requestData: LoginRequest) : Response<LoginResponse>





}