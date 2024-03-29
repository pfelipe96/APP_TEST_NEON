package com.example.app_test_neon.my_profile.presenter

import com.example.app_test_neon.data.InfoProfile
import io.reactivex.Single

interface InterfaceToPresenter{
    fun getToken() : Single<String>
    fun getInfoProfile(): Single<InfoProfile>
}