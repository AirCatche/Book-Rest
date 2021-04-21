package com.slobodyanyuk_mykhailo99.bookrest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
const val ROLE_USER = 0
const val ROLE_ADMIN = 1

@Entity
data class Role(
    var id: Int? = null,
    var name: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var status: String? = null) {

    @PrimaryKey(autoGenerate = false)
    var rid: Int = ROLE_USER
}