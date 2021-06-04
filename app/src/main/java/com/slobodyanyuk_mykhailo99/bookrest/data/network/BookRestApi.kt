package com.slobodyanyuk_mykhailo99.bookrest.data.network

import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.LoginRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.SignUpRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.LoginResponse
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.SignUpResponse
import com.slobodyanyuk_mykhailo99.bookrest.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface BookRestApi {

    companion object {
        operator fun invoke(connectionInterceptor: ConnectionInterceptor) : BookRestApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectionInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookRestApi::class.java)
        }
    }

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun userSignUp(@Body request: SignUpRequest) : Response<SignUpResponse>

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun userLogin(@Body request: LoginRequest) : Response<LoginResponse>





}