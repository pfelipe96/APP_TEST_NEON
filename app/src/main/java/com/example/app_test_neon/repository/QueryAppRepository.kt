package com.example.app_test_neon.repository

interface QueryAppRepository{
    fun setLong(key: String, value: Long)
    fun setInt(key: String, value: Int)
    fun setBoolean(key: String, value: Boolean)
    fun setString(key: String, value: String)

    fun getString(key: String): String
    fun getInt(key: String): Int
    fun getBoolean(key: String): Boolean
    fun getLong(key: String): Long

    fun clearData()
}