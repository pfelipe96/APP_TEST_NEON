package com.example.app_test_neon.utils

import androidx.room.TypeConverter
import com.example.app_test_neon.data.DataGetTransfers
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class Converters {

    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        return if (value != null) {
            val listType = object : TypeToken<ArrayList<String>>() {}.type
            Gson().fromJson<ArrayList<String>>(value, listType) ?: arrayListOf()
        } else {
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>?): String {
        return if (list != null) {
            val gson = Gson()
            gson.toJson(list) ?: ""
        } else {
            ""
        }
    }

    // -------

    @TypeConverter
    fun stringToDate(value: String?): Date {
        return if (value != null) {
            val date = object : TypeToken<Date>() {}.type
            Gson().fromJson<Date>(value, date) ?: Date()
        } else {
            Date()
        }
    }

    @TypeConverter
    fun dateToString(date: Date?): String {
        return if (date != null) {
            val gson = Gson()
            gson.toJson(date) ?: ""
        } else {
            ""
        }
    }


    // -------

    @TypeConverter
    fun dataGetTransfersToString(eventsData: DataGetTransfers?): String {
        return if (eventsData != null) {
            val gson = Gson()
            gson.toJson(eventsData) ?: ""
        } else {
            ""
        }
    }

    @TypeConverter
    fun stringToDataGetTransfers(it: String?): DataGetTransfers {
        return if (it != null) {
            val listType = object : TypeToken<DataGetTransfers>() {}.type
            Gson().fromJson<DataGetTransfers>(it, listType) ?: DataGetTransfers()
        } else {
            DataGetTransfers()
        }
    }
}