package com.slobodyanyuk_mykhailo99.bookrest.data.repositories

import com.slobodyanyuk_mykhailo99.bookrest.data.db.BookRestDatabase
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User
import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.LoginRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.network.requests.SignUpRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.network.BookRestApi
import com.slobodyanyuk_mykhailo99.bookrest.data.network.BookRestRequest
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.LoginResponse
import com.slobodyanyuk_mykhailo99.bookrest.data.network.responses.SignUpResponse
import com.slobodyanyuk_mykhailo99.bookrest.preference.PreferenceProvider

class UserRepository(private val api: BookRestApi,
                     private val db: BookRestDatabase,
                     private val preferences: PreferenceProvider): BookRestRequest() {

    suspend fun userLogin(loginRequest: LoginRequest) : LoginResponse {
        return apiRequest {
            api.userLogin(loginRequest)
        }
    }
    suspend fun userSignUp(signUpRequest: SignUpRequest) : SignUpResponse {
        return apiRequest {
            api.userSignUp(signUpRequest)
        }
    }


    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()

}