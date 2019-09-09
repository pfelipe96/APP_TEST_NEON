package com.example.app_test_neon.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class DataGetTransfers(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("ClientId") val idCustomer: Int = -1,
    @SerializedName("Valor") val value: Double = -1.0,
    @SerializedName("Token") val token: Int,
    @SerializedName("Data") val date: Date? = null
)