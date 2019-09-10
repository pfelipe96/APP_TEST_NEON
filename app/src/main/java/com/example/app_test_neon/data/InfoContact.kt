package com.example.app_test_neon.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InfoContact(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("name") val name: String = "",
    @SerializedName("phone") val phone: String = "",
    @SerializedName("image_profile") val imageProfile: String = ""
) : Serializable