package com.example.app_test_neon.my_profile.presenter

import android.util.Log
import com.example.app_test_neon.data.InfoProfile
import com.example.app_test_neon.my_profile.jobs.GetTokenJob
import com.example.app_test_neon.repository.QueryAppRepository
import io.reactivex.Single

import javax.inject.Inject

class PresenterHub @Inject constructor(
    private val getTokenJob: GetTokenJob,
    private val queryAppRepository: QueryAppRepository
) : InterfaceToPresenter {

    init {
        getTokenJob.getTokenFromRepository()
    }

    override fun getInfoProfile(): Single<InfoProfile> {
        return Single.create {
            val name = queryAppRepository.getString("name")
            val mail = queryAppRepository.getString("mail")
            val imageProfile = queryAppRepository.getString("image_profile")

            it.onSuccess(InfoProfile(name, mail, imageProfile))
        }
    }

    override fun getToken() {
        getTokenJob
            .getTokenFromRepository()
            .doOnSuccess { Log.v("Token", it) }
            .subscribe()
    }
}