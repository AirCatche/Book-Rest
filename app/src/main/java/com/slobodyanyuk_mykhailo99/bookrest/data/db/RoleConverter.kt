package com.slobodyanyuk_mykhailo99.bookrest.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Role
import java.lang.reflect.Type
import java.util.*

class RoleConverter {
    @TypeConverter
    fun toRoles(value: String?): Roles {
        if (value.isNullOrEmpty()) {
            return Roles()
        }
        val splitRoles: List<String> = value.split(",")
        val roles = ArrayList<String>()
        for (item in splitRoles) {
            roles.add(item)
        }
        return Roles(roles)
    }

    @TypeConverter
    fun toString(roles: Roles?): String {
        var string = ""
        if (roles == null) {
            return string
        }
        roles.roles.forEach() {
            string += "$it,"
        }
        return string
    }
}
//class RoleConverter {
//    val gson = Gson()
//    //    @Suppress("UNCHECKED_CAST")
//    @TypeConverter
//    fun toRoles(value: String?) : List<Role> {
//        if (value.isNullOrEmpty()) {
//            return Collections.emptyList()
//        }
////        val splitRoles: List<String> = value.split(",")
////        val roles = ArrayList<String>()
////        for (item in splitRoles) {
////            roles.add(item)
////        }
//        val listRoles: Type = object : TypeToken<List<Role?>?>() {}.type
//        return gson.fromJson(value, listRoles)
//    }
//    @TypeConverter
//    fun toString(roles: List<Role>): String {
////        var string = ""
////        if (users == null) {
////            return string
////        }
////        users.users.forEach {
////            string += "$it,"
////        }
//        return gson.toJson(roles)
//    }
//}