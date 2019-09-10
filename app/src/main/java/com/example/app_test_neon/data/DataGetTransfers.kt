package com.example.app_test_neon.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
@Entity(tableName = "DataGetTransfers")
data class DataGetTransfers(
    @PrimaryKey
    @ColumnInfo(name = "id") @SerializedName("id") val id: Int = -1,
    @ColumnInfo(name ="ClientId") @SerializedName("ClientId") val idCustomer: Int = -1,
    @ColumnInfo(name ="Valor") @SerializedName("Valor") val value: Double = -1.0,
    @ColumnInfo(name ="Token") @SerializedName("Token") val token: String = "",
    @ColumnInfo(name = "Data") @SerializedName("Data") val date: Date = Date()
)