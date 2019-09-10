package com.example.app_test_neon.contact_profiles.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_test_neon.MainApplication
import com.example.app_test_neon.R
import com.example.app_test_neon.contact_profiles.adapter.AdapterContacts
import com.example.app_test_neon.contact_profiles.fragment.DialogFragmentToSendMoney
import com.example.app_test_neon.contact_profiles.presenter.PresenterHubContact
import com.example.app_test_neon.data.InfoContact
import com.example.app_test_neon.extensions.gone
import com.example.app_test_neon.extensions.isConnected
import com.example.app_test_neon.extensions.show
import com.example.app_test_neon.package_enum.StatusApi
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_contact_profile.*
import javax.inject.Inject

class ContactProfile : AppCompatActivity() {

    @Inject
    lateinit var presenterHubContacts: PresenterHubContact

    companion object{
        private const val RECYCLER_VIEW_CONTACT = "RECYCLER_VIEW_CONTACT"
        private const val ALL = "ALL"
    }

    private val adapterContacts by lazy {
        AdapterContacts()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_profile)
        setButton()
        injectDependencies()
        setInitializeComponent()
    }

    private fun setInitializeComponent() {
        if (isConnected()) {
            setListToAdapter()
        } else
            handlerState(StatusApi.FAIL, ALL)
    }

    private fun setButton() {
        btn_to_icon_back.setOnClickListener { onBackPressed() }
        reload_to_contact?.setOnClickListener { setInitializeComponent() }
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
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    if (it.isEmpty().not())
                        handlerState(StatusApi.SUCCESS, RECYCLER_VIEW_CONTACT)
                }
                .doOnError { handlerState(StatusApi.FAIL, "recycler_view") }
        )

        adapterContacts.observableContact()
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnNext(::showDialogFragment)
            .ignoreElements()
            .onErrorComplete()
            .doOnError { Log.e("error_contact_profile", it.message.toString()) }
            .subscribe()
    }

    private fun showDialogFragment(infoContact: InfoContact){
        DialogFragmentToSendMoney.newInstance(
            id = infoContact.id,
            name = infoContact.name,
            phone = infoContact.phone,
            image = infoContact.imageProfile
        ).show(supportFragmentManager, "DIALOG_FRAGMENT_TO_SEND_MONEY")
    }

    private fun handlerState(statusApi: StatusApi, from: String) {
        when {
            statusApi == StatusApi.INITIAL && from == RECYCLER_VIEW_CONTACT -> {
                recycler_view_contacts?.gone()
                container_fail_to_contact?.gone()
                container_loading_contact?.show()
            }
            statusApi == StatusApi.SUCCESS && from == RECYCLER_VIEW_CONTACT -> {
                container_loading_contact?.gone()
                container_fail_to_contact?.gone()
                recycler_view_contacts?.show()
            }
            statusApi == StatusApi.FAIL && from == ALL -> {
                container_loading_contact?.gone()
                recycler_view_contacts?.gone()
                container_fail_to_contact?.show()
            }
            statusApi == StatusApi.LOADING && from == RECYCLER_VIEW_CONTACT -> {
                recycler_view_contacts?.gone()
                container_fail_to_contact?.gone()
                container_loading_contact?.show()
            }
            statusApi == StatusApi.LIST_EMPTY && from == RECYCLER_VIEW_CONTACT -> {
                container_loading_contact?.gone()
                recycler_view_contacts?.gone()
                container_fail_to_contact?.gone()
            }
        }
    }
}
