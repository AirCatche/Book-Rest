package com.slobodyanyuk_mykhailo99.bookrest.data.db.entity

import androidx.room.Entity

@Entity(primaryKeys = ["uid", "rid"])
data class UserRoleCrossRef(
    val uid: Long,
    val rid: Long)