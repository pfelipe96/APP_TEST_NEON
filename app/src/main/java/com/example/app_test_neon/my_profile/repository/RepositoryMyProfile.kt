package com.example.app_test_neon.my_profile.repository

import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class RepositoryMyProfile @Inject constructor() {
    fun getToken(name: String, mail: String): Single<String> =
        mockTokenSuccess(name, mail)
}

private fun mockTokenSuccess(name: String, mail: String): Single<String> =
    Single.create {
        if(name == "Paulo Felipe" && mail == "p.felipe@msn.com") {
            val token = UUID.randomUUID().toString()
            it.onSuccess(token)
        }else{
            it.onError(Throwable("user not found"))
        }
    }
