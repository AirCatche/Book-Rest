package com.slobodyanyuk_mykhailo99.bookrest.data.db

import androidx.room.TypeConverter

class UserConverter {
    @Suppress("UNCHECKED_CAST")
    @TypeConverter
    fun toUsers (value: String?) : Users {
        if (value.isNullOrEmpty()) {
            return Users()
        }
        val splitRoles: List<String> = value.split(",")
        val roles = ArrayList<String>()
        for (item in splitRoles) {
            roles.add(item)
        }
        return Users(roles)
    }
    @TypeConverter
    fun toString(users: Users?): String {
        var string = ""
        if (users == null) {
            return string
        }
        users.users.forEach {
            string += "$it,"
        }
        return string
    }
}