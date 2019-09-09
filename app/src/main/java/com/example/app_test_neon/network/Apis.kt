package com.example.app_test_neon.network

import com.example.app_test_neon.data.DataGetTransfers
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Apis {

    @GET("GenerateToken")
    fun getToken(
        @Query("nome") name: String,
        @Query("email") mail: String
    ): Single<String>

    @POST("SendMoney")
    fun sendMoney(
        @Query("ClienteId") idCustomer: String,
        @Query("token") token: String,
        @Query("valor") value: Double
    ): Single<Boolean>

    @GET("GetTransfers")
    fun getTransfers(
        @Query("token") token: String
    ): Single<List<DataGetTransfers>>
}
