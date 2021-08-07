package com.slobodyanyuk_mykhailo99.bookrest.data.db.entity

import androidx.room.Entity

@Entity
data class Restaurant(
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var tags: List<String>? = null,
    var isLiked: String? = null,
    var reviews: List<Review>? = null,
    var photos: List<String>? = null,
    var menu: List<String>? = null,
    var description: String? = null,
    var schedule: String? = null,
    var cuisine: String? = null,
)
