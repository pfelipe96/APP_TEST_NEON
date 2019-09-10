package com.example.app_test_neon.contact_profiles.jobs

import com.example.app_test_neon.contact_profiles.repository.RepositoryContactProfiles
import com.example.app_test_neon.data.DataGetTransfers
import javax.inject.Inject

class SendMoney @Inject constructor(
    private val repositoryContactProfiles: RepositoryContactProfiles
){
    fun setValue(dataGetTransfers: DataGetTransfers){
        repositoryContactProfiles
            .setInsertMoney(dataGetTransfers)
    }
}