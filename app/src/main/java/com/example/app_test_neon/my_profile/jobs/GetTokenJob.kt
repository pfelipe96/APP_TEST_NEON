package com.example.app_test_neon.my_profile.jobs

import android.util.Log
import com.example.app_test_neon.my_profile.repository.RepositoryMyProfile
import com.example.app_test_neon.repository.QueryAppRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetTokenJob @Inject constructor(
    private val queryAppRepository: QueryAppRepository,
    private val repositoryMyProfile: RepositoryMyProfile
) {
    fun getTokenFromRepository() : Single<String> {
        val name = queryAppRepository.getString("name")
        val mail = queryAppRepository.getString("mail")

        return repositoryMyProfile
            .getToken(name, mail)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { queryAppRepository.setString("token", it) }
            .doOnError { Log.e("error_get_token", it.message) }
    }
}