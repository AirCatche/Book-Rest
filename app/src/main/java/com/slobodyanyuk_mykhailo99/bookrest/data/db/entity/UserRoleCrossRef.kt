package com.slobodyanyuk_mykhailo99.bookrest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slobodyanyuk_mykhailo99.bookrest.data.db.Roles

@Entity(primaryKeys = ["uid", "rid"])
data class UserRoleCrossRef(
    val uid: Long,
    val rid: Long)