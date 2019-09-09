package com.example.app_test_neon.my_profile.presenter

import com.example.app_test_neon.data.InfoProfile
import com.example.app_test_neon.my_profile.jobs.GetTokenJob
import com.example.app_test_neon.repository.QueryAppRepository
import io.reactivex.Single

import javax.inject.Inject

class PresenterHub @Inject constructor(
    private val getTokenJob: GetTokenJob,
    private val queryAppRepository: QueryAppRepository
) : InterfaceToPresenter {

    override fun getInfoProfile(): Single<InfoProfile> {
        return Single.create {
            val name = queryAppRepository.getString("name")
            val mail = queryAppRepository.getString("mail")
            val imageProfile = queryAppRepository.getString("image_profile")

            if (name.isEmpty().not() && mail.isEmpty().not() && imageProfile.isEmpty().not())
                it.onSuccess(InfoProfile(name, mail, imageProfile))
            else
                it.onError(Throwable("user not found"))
        }
    }

    override fun getToken(): Single<String> =
        getTokenJob.getTokenFromRepository()
}