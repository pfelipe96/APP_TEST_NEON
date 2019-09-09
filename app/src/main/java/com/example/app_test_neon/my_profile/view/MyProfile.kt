package com.example.app_test_neon.my_profile.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.app_test_neon.MainApplication
import com.example.app_test_neon.R
import com.example.app_test_neon.contact_profiles.view.ContactProfile
import com.example.app_test_neon.extensions.gone
import com.example.app_test_neon.extensions.loadImage
import com.example.app_test_neon.extensions.show
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        injectDependencies()
        setInitializeProfile()
        setButton()
    }

    private fun injectDependencies() {
        val mainApplication = application as MainApplication
        mainApplication.applicationComponent.inject(this)
    }

    private fun setButton(){
        send_money.setOnClickListener { startActivity(Intent(it.context, ContactProfile::class.java)) }
    }

    private fun setInitializeProfile(){
        handlerState(StatusApi.INITIAL)

        presenterHub.getInfoProfile()
            .subscribeOn(Schedulers.io())
            .delaySubscription(1500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                name_my_profile.text = it.name
                mail_my_profile.text = it.mail
                image_my_profile.loadImage(it.imageProfile)
                handlerState(StatusApi.SUCCESS)
            }
            .doOnError { Log.e("error_my_profile", it.message) }
            .subscribe()
    }


    private fun handlerState(statusApi: StatusApi){
        when(statusApi){
            StatusApi.INITIAL -> {
                loading_my_profile?.show()
                my_profile_loaded?.gone()
            }
            StatusApi.SUCCESS -> {
                loading_my_profile?.gone()
                my_profile_loaded?.show()
            }
            StatusApi.FAIL -> {
                loading_my_profile?.gone()
                my_profile_loaded?.gone()
            }
            StatusApi.LOADING -> {
                loading_my_profile?.show()
                my_profile_loaded?.gone()
            }
        }
    }

}
