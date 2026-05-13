package com.hrconnect.android

import android.app.Application
import com.hrconnect.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HrConnectApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HrConnectApp)
            modules(appModule)
        }
    }
}