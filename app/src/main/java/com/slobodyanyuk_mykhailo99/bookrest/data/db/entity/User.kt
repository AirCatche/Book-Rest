package com.slobodyanyuk_mykhailo99.bookrest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity()
data class User(
    var id: Int? = null,
    var username: String? = null,
    var email: String? = null,
    var password: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var status: String? = null,
    var created: String? = null,
    var updated: String? = null,
    var verified: String? = null,
    var verificationTimesAsked: String? = null
//    var roles : ArrayList<Role>? = null
    ) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}