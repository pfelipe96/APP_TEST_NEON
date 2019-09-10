package com.example.app_test_neon.contact_profiles.presenter

import com.example.app_test_neon.contact_profiles.repository.RepositoryContactProfiles
import com.example.app_test_neon.data.DataGetTransfers
import com.example.app_test_neon.data.InfoContact
import com.example.app_test_neon.repository.QueryAppRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PresenterHubContact @Inject constructor(
    private val repositoryContactProfiles: RepositoryContactProfiles,
    private val queryAppRepository: QueryAppRepository
) : InterfacePresenterHubContact {
    fun getListContact() : Single<List<InfoContact>> =
        repositoryContactProfiles.getListContact()

    fun sendValue(dataGetTransfers: DataGetTransfers) : Single<Boolean>{
        val token = queryAppRepository.getString("token")

        return repositoryContactProfiles
            .setInsertMoney(dataGetTransfers
                .copy(token = token)
                .copy(date = Date())
            )
            .subscribeOn(Schedulers.io())
    }

}