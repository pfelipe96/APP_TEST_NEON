package com.example.app_test_neon.data

import com.google.gson.annotations.SerializedName

data class InfoContact(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("image_profile") val imageProfile: String
)