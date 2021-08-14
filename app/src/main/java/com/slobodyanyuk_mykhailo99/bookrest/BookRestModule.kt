package com.slobodyanyuk_mykhailo99.bookrest

import android.content.Context
import androidx.room.Room
import com.slobodyanyuk_mykhailo99.bookrest.data.db.BookRestDatabase
import javax.inject.Singleton
import com.slobodyanyuk_mykhailo99.bookrest.data.network.BookRestApi
import com.slobodyanyuk_mykhailo99.bookrest.data.network.ConnectionInterceptor
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object BookRestModule {

    @Provides
    @Singleton
    fun provideBookRestApi(connectionInterceptor: ConnectionInterceptor,
        ): BookRestApi {
         val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(connectionInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookRestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BookRestDatabase {
        return Room.databaseBuilder(context.applicationContext, BookRestDatabase::class.java, "BookRest.db").build()

    }

    @Provides
    @Singleton
    fun provideRepository(brApi: BookRestApi, db: BookRestDatabase): UserRepository {
        return UserRepository(brApi,db)
    }

}