package com.slobodyanyuk_mykhailo99.bookrest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_USER_ID = 0

@Entity
data class User(
    var id: Int? = null,
    var username: String? = null,
    var email: String? = null,
    var password: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var roles :List<Role>? = null) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}