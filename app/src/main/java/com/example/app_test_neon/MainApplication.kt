package com.example.app_test_neon

import android.annotation.SuppressLint
import android.app.Application
import com.example.app_test_neon.di.component.ApplicationComponent
import com.example.app_test_neon.di.component.DaggerApplicationComponent
import com.example.app_test_neon.di.module.ApplicationModule
import com.example.app_test_neon.di.module.NetworkModule
import com.example.app_test_neon.repository.QueryAppRepository
import javax.inject.Inject

@SuppressLint("Registered")
class MainApplication: Application(){

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var repository: QueryAppRepository

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()

        applicationComponent.inject(this@MainApplication)

        repository.setString("name", "Paulo Felipe")
        repository.setString("mail", "p.felipe@msn.com")
        repository.setString("image_profile", "https://media.licdn.com/dms/image/C4E03AQFomNyLAfabEw/profile-displayphoto-shrink_200_200/0?e=1573689600&v=beta&t=Wo1NuWRNuuIfoKLi-SmEAeDNoHm9JPeTZcLJuhsm8Jo")
    }
}