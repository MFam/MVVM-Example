package com.mfamstory.rxmvvmtest

import android.app.Application
import android.util.Log
import com.mfamstory.rxmvvmtest.di.apiModule
import com.mfamstory.rxmvvmtest.di.networkModule
import com.mfamstory.rxmvvmtest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppInit : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(networkModule)
            modules(apiModule)
            modules(viewModelModule)
        }
    }
}