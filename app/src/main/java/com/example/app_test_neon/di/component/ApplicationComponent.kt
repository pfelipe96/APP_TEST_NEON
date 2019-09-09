package com.example.app_test_neon.di.component

import com.example.app_test_neon.MainApplication
import com.example.app_test_neon.contact_profiles.view.ContactProfile
import com.example.app_test_neon.di.module.ApplicationModule
import com.example.app_test_neon.di.module.NetworkModule
import com.example.app_test_neon.my_profile.view.MyProfile
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(application: MainApplication)
    fun inject(myProfile: MyProfile)
    fun inject(contactProfile: ContactProfile)
}