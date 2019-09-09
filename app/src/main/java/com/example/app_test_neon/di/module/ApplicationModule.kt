package com.example.app_test_neon.di.module

import android.app.Application
import com.example.app_test_neon.repository.AppRepository
import com.example.app_test_neon.repository.QueryAppRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(application: Application) {
    var applicationInst = application

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return applicationInst
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideAppRepository() : QueryAppRepository = AppRepository(applicationInst)
}