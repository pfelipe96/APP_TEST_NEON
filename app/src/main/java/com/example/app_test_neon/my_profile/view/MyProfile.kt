package com.example.app_test_neon.my_profile.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app_test_neon.MainApplication
import com.example.app_test_neon.R
import com.example.app_test_neon.contact_profiles.view.ContactProfile
import com.example.app_test_neon.extensions.*
import com.example.app_test_neon.my_profile.presenter.PresenterHub
import com.example.app_test_neon.package_enum.StatusApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_my_profile.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MyProfile : AppCompatActivity() {

    @Inject
    lateinit var presenterHub: PresenterHub

    companion object{
        private const val GENERATE_TOKEN = "GENERATE_TOKEN"
        private const val PROFILE = "PROFILE"
        private const val ALL = "ALL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        injectDependencies()
        setInitializeComponent()
        setButton()
    }

    private fun injectDependencies() {
        val mainApplication = application as MainApplication
        mainApplication.applicationComponent.inject(this)
    }

    private fun setButton() {
        send_money?.setOnClickListener {
            val intent = Intent(it.context, ContactProfile::class.java)
            it.context.startActivity(intent)
        }
        reload?.setOnClickListener { setInitializeComponent() }
    }

    private fun setInitializeComponent() {
        if(isConnected()) {
            setInitializeProfile()
            setInitializeGenerateToken()
        }else{
            handlerState(StatusApi.FAIL, "profile")
            Toast.makeText(this, "Verifique sua internet.", Toast.LENGTH_LONG).show()
        }
    }

    private fun setInitializeGenerateToken() {
        handlerState(StatusApi.INITIAL, GENERATE_TOKEN)

        presenterHub.getToken()
            .subscribeOn(Schedulers.io())
            .delaySubscription(3000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { handlerState(StatusApi.SUCCESS, GENERATE_TOKEN) }
            .doOnError { handlerState(StatusApi.FAIL, GENERATE_TOKEN) }
            .ignoreElement()
            .onErrorComplete()
            .subscribe()
    }

    private fun setInitializeProfile() {
        handlerState(StatusApi.INITIAL, PROFILE)

        presenterHub.getInfoProfile()
            .subscribeOn(Schedulers.io())
            .delaySubscription(1500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                name_my_profile.text = it.name
                mail_my_profile.text = it.mail
                image_my_profile.loadImage(it.imageProfile)
                handlerState(StatusApi.SUCCESS, PROFILE)
            }
            .doOnError { handlerState(StatusApi.FAIL, ALL)}
            .ignoreElement()
            .onErrorComplete()
            .subscribe()
    }


    private fun handlerState(statusApi: StatusApi, from: String) {
        when {
            statusApi == StatusApi.INITIAL && from == PROFILE -> {
                my_profile_loaded?.gone()
                container_fail_to_my_profile?.gone()
                loading_my_profile?.show()
            }
            statusApi == StatusApi.SUCCESS && from == PROFILE -> {
                loading_my_profile?.gone()
                container_fail_to_my_profile?.gone()
                my_profile_loaded?.show()
            }
            statusApi == StatusApi.FAIL && from == ALL -> {
                loading_my_profile?.gone()
                my_profile_loaded?.gone()
                container_fail_to_my_profile?.show()
            }
            statusApi == StatusApi.LOADING && from == PROFILE -> {
                my_profile_loaded?.gone()
                loading_my_profile?.gone()
                loading_my_profile?.show()
            }
            statusApi == StatusApi.SUCCESS && from == GENERATE_TOKEN -> {
                loading_to_generate_token?.gone()
                status_generate_token.apply {
                    color(android.R.color.holo_green_dark)
                    text = context.getString(R.string.success)
                }
                send_money.isEnabled = true
                history_of_sends.isEnabled = true
            }
            (statusApi == StatusApi.LOADING || statusApi == StatusApi.INITIAL) && from == GENERATE_TOKEN -> {
                loading_to_generate_token?.show()
                status_generate_token.apply {
                    color(R.color.yellow)
                    text = context.getString(R.string.genarete_token)
                }
                send_money.isEnabled = false
                history_of_sends.isEnabled = false
            }
            statusApi == StatusApi.FAIL && from == GENERATE_TOKEN -> {
                loading_to_generate_token?.gone()
                status_generate_token.apply {
                    color(android.R.color.holo_red_dark)
                    text = context.getString(R.string.fail_genarete_token)
                }
                send_money.isEnabled = false
                history_of_sends.isEnabled = false
            }
        }
    }

}
