package com.example.app_test_neon.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app_test_neon.data.DataGetTransfers
import com.example.app_test_neon.utils.Converters

@Database(entities = [DataGetTransfers::class], version = 1)
@TypeConverters(Converters::class)
abstract class SendMoneyDatabase: RoomDatabase(){
    abstract fun controlDatabaseDAO(): ControlDatabaseDAO
}