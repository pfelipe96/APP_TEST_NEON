package com.example.app_test_neon.my_profile.repository

import com.example.app_test_neon.network.Apis
import io.reactivex.Single
import javax.inject.Inject

class RepositoryMyProfile @Inject constructor(
    private val apis: Apis
) {
    fun getToken(name: String, mail: String): Single<String> =
        apis.getToken(name = name, mail = mail)
}
