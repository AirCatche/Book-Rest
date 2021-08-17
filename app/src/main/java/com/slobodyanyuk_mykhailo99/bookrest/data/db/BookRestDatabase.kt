package com.slobodyanyuk_mykhailo99.bookrest.data.db

import androidx.room.RoomDatabase;
import androidx.room.Database;
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Role
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User

@Database(entities = [User::class,Role::class], version = 1, exportSchema = false)
abstract class BookRestDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

}