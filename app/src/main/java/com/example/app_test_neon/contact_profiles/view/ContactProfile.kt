package com.example.app_test_neon.contact_profiles.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_test_neon.MainApplication
import com.example.app_test_neon.R
import com.example.app_test_neon.contact_profiles.adapter.AdapterContacts
import com.example.app_test_neon.contact_profiles.presenter.PresenterHubContact
import com.example.app_test_neon.extensions.gone
import com.example.app_test_neon.extensions.show
import com.example.app_test_neon.package_enum.StatusApi
import kotlinx.android.synthetic.main.activity_contact_profile.*
import javax.inject.Inject

class ContactProfile : AppCompatActivity() {

    @Inject
    lateinit var presenterHubContacts: PresenterHubContact

    private val adapterContacts by lazy {
        AdapterContacts()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_profile)
        setButton()
        injectDependencies()
        setListToAdapter()
    }

    private fun setButton(){
        btn_to_icon_back.setOnClickListener { onBackPressed() }
    }

    private fun injectDependencies() {
        val mainApplication = application as MainApplication
        mainApplication.applicationComponent.inject(this)
    }

    private fun setListToAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        recycler_view_contacts.apply {
            this.show()
            this.layoutManager = linearLayoutManager
            this.itemAnimator = DefaultItemAnimator()
            this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            this.setHasFixedSize(true)
            this.adapter = adapterContacts
        }

        adapterContacts.setAdapter(
            presenterHubContacts
                .getListContact()
                .doOnSuccess { if (it.isEmpty().not()) handlerState(StatusApi.SUCCESS) }
                .doOnError { handlerState(StatusApi.FAIL) }
        )
    }


    private fun handlerState(statusApi: StatusApi) {
        when (statusApi) {
            StatusApi.INITIAL -> {
                recycler_view_contacts.gone()
            }
            StatusApi.SUCCESS -> {
                recycler_view_contacts.show()
            }
            StatusApi.FAIL -> {
                recycler_view_contacts.gone()
            }
            StatusApi.LOADING -> {
                recycler_view_contacts.gone()
            }
        }
    }
}
