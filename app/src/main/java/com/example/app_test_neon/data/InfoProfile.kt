package com.example.app_test_neon.data

import com.google.gson.annotations.SerializedName

data class InfoProfile(
    @SerializedName("name") val name: String,
    @SerializedName("mail") val mail: String,
    @SerializedName("image_profile") val imageProfile: String
)