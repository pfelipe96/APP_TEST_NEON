package com.example.app_test_neon.di.module

import com.example.app_test_neon.network.Apis
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module(includes = [ApplicationModule::class])
class NetworkModule {

    @Provides
    @Named("ProcessoSeletivo")
    fun provideProcessoSelectivo(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://processoseletivoneon.azurewebsites.net/")
            .build()
    }

    @Provides
    fun provideSeatGeekService(@Named("ProcessoSeletivo") apis: Retrofit): Apis {
        return apis.create(Apis::class.java)
    }
}