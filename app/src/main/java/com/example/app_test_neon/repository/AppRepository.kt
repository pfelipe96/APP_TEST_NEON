package com.example.app_test_neon.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class AppRepository(application: Application) : QueryAppRepository {

    private val userInfo: String = "user_info"
    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = application.getSharedPreferences(userInfo, Context.MODE_PRIVATE)
    }

    override fun setLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String = sharedPreferences.getString(key, "") ?: ""

    override fun getInt(key: String): Int = sharedPreferences.getInt(key, -1)

    override fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    override fun getLong(key: String): Long = sharedPreferences.getLong(key, -1)

    override fun clearData() {
        sharedPreferences.edit().clear().apply()
    }
}