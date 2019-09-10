package com.example.app_test_neon.contact_profiles.repository

import com.example.app_test_neon.contact_profiles.objects.ListContacts
import com.example.app_test_neon.data.DataGetTransfers
import com.example.app_test_neon.data.InfoContact
import com.example.app_test_neon.room.SendMoneyDatabase
import io.reactivex.Single
import javax.inject.Inject

class RepositoryContactProfiles @Inject constructor(
    private val providesSendMoneyDatabase: SendMoneyDatabase
) {
    fun getListContact(): Single<List<InfoContact>> =
        Single.fromCallable { ListContacts.contacts }

    fun setInsertMoney(dataGetTransfers: DataGetTransfers): Single<Boolean> =
        Single.create {
            if (dataGetTransfers.value > 0 && dataGetTransfers.token.isEmpty().not() && dataGetTransfers.idCustomer > 0) {
                providesSendMoneyDatabase.controlDatabaseDAO().insertDataGetTransfers(dataGetTransfers)
                it.onSuccess(true)
            } else {
                it.onError(Throwable("missing variables"))
            }
        }
}