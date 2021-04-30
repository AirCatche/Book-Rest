package com.slobodyanyuk_mykhailo99.bookrest.data.db


import android.content.Context
import androidx.room.RoomDatabase;
import androidx.room.Database;
import androidx.room.Room
import androidx.room.TypeConverters
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.Role
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User

@Database(entities = [User::class,Role::class], version = 1, exportSchema = false)
@TypeConverters(RoleConverter::class, UserConverter::class)
abstract class BookRestDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    companion object {
        @Volatile
        private var instance: BookRestDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?:buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, BookRestDatabase::class.java, "BookRest.db").build()
    }

}