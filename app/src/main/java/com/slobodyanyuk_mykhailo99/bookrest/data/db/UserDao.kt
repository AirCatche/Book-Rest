package com.slobodyanyuk_mykhailo99.bookrest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.CURRENT_USER_ID
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")

    fun getUser() : LiveData<User>


}