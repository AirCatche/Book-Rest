package com.slobodyanyuk_mykhailo99.bookrest.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.slobodyanyuk_mykhailo99.bookrest.util.PreferenceKeys.KEY_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PreferenceProvider @Inject constructor(
    @ApplicationContext context: Context,
){

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveToken(token: String) = preference.edit().putString(KEY_TOKEN, token).apply()

    fun getToken () = preference.getString(KEY_TOKEN, "")
}