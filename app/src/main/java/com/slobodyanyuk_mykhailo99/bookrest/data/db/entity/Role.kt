package com.slobodyanyuk_mykhailo99.bookrest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_ROLE_ID = 0
@Entity()
data class Role(
    var id: Int? = null,
    var name: String? = null,
    var created: String? = null,
    var updated: String? = null,
    var status: String? = null,
   // var users : ArrayList<User>? = null
) {
    @PrimaryKey(autoGenerate = false)
    var rid: Int = CURRENT_ROLE_ID
}