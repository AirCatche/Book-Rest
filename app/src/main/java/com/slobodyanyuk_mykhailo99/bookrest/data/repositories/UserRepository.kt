package com.slobodyanyuk_mykhailo99.bookrest.data.repositories

import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.LoginData
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.SignUpData
import com.slobodyanyuk_mykhailo99.bookrest.data.network.BookRestApi
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.LoginResponse
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.SignUpResponse
import retrofit2.Call
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(loginData: LoginData) : Response<LoginResponse> {
        return BookRestApi().userLogin(loginData)
    }
    suspend fun userSignUp(signUpData: SignUpData) : Response<SignUpResponse> {
        return BookRestApi().userSignUp(signUpData)
    }

}