package com.example.app_test_neon.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app_test_neon.data.DataGetTransfers

@Dao
interface ControlDatabaseDAO{
    @Query("SELECT * FROM DataGetTransfers")
    fun getAllSendMoney() : List<DataGetTransfers>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataGetTransfers(vararg dataGetTransfers: DataGetTransfers)

    @Query("DELETE FROM DataGetTransfers")
    fun deleteAllDataGetTransfers()

    @Query("DELETE FROM DataGetTransfers WHERE id = :id")
    fun deleteDataGetTransfers(id: String)

    @Query("SELECT * FROM DataGetTransfers WHERE id = :id")
    fun getDataGetTransfers(id: String) : DataGetTransfers
}