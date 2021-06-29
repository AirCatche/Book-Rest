package com.slobodyanyuk_mykhailo99.bookrest.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.slobodyanyuk_mykhailo99.bookrest.util.Constants


class PreferenceProvider (context: Context){

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveToken(token: String) = preference.edit().putString(Constants.KEY_TOKEN, token).apply()

    fun getToken () = preference.getString(Constants.KEY_TOKEN, "")
}