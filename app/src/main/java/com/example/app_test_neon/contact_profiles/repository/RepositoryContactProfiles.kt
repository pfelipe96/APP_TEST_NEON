package com.example.app_test_neon.contact_profiles.repository

import com.example.app_test_neon.contact_profiles.objects.ListContacts
import com.example.app_test_neon.data.InfoContact
import io.reactivex.Single
import javax.inject.Inject

class RepositoryContactProfiles @Inject constructor() {
    fun getListContact(): Single<List<InfoContact>> =
        Single.fromCallable { ListContacts.contacts }
}