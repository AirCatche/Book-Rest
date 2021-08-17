package com.slobodyanyuk_mykhailo99.bookrest.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.slobodyanyuk_mykhailo99.bookrest.data.db.entity.User
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookRestDatabaseTest: TestCase() {

    private lateinit var db: BookRestDatabase
    private lateinit var dao: UserDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BookRestDatabase::class.java).build()
        dao = db.getUserDao()
    }

    @After
    fun closeDb(){
        db.close()
    }

    @Test
    fun userCRUDTest() = runBlocking {
        var dbUser: User?

        // TEST IF USER IS NULL
        dbUser = dao.getUser()
        assertThat(dbUser).isNull()

        //TEST ADDING USER
        val firstUser: User = User(0,"test","test@test.test","testtest","null","null","ACTIVE","2021-08-16T12:59:44.320+00:00","2021-08-16T12:59:44.320+00:00","false","1")
        dao.upsert(firstUser)
        dbUser = dao.getUser()
        assertThat(firstUser == dbUser).isTrue()

        //TEST Update strategy replace USER
        val secondUser: User = User(0,"testTWO","test@test.test","testtest","null","null","ACTIVE","2021-08-16T12:59:44.320+00:00","2021-08-16T12:59:44.320+00:00","false","1")
        dao.upsert(secondUser)
        dbUser = dao.getUser()
        assertThat(firstUser == dbUser).isFalse()

        //TEST DELETING USER
        dao.deleteUser()
        dbUser = dao.getUser()
        assertThat(dbUser).isNull()
    }

}