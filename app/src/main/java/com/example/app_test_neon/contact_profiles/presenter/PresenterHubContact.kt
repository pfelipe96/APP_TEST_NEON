package com.example.app_test_neon.contact_profiles.presenter

import com.example.app_test_neon.contact_profiles.repository.RepositoryContactProfiles
import com.example.app_test_neon.data.InfoContact
import io.reactivex.Single
import javax.inject.Inject

class PresenterHubContact @Inject constructor(
    private val repositoryContactProfiles: RepositoryContactProfiles
) : InterfacePresenterHubContact {
    fun getListContact() : Single<List<InfoContact>> =
        repositoryContactProfiles.getListContact()
}