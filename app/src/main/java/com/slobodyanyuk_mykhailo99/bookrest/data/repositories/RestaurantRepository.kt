package com.slobodyanyuk_mykhailo99.bookrest.data.repositories

import com.slobodyanyuk_mykhailo99.bookrest.data.db.BookRestDatabase
import com.slobodyanyuk_mykhailo99.bookrest.data.network.BookRestApi
import com.slobodyanyuk_mykhailo99.bookrest.data.network.BookRestRequest

import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: BookRestApi,
    private val db: BookRestDatabase,
): BookRestRequest() {



}