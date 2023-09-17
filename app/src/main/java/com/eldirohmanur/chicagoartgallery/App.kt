package com.eldirohmanur.chicagoartgallery

import android.app.Application
import com.eldirohmanur.chicagoartgallery.core.di.coreModule
import com.eldirohmanur.chicagoartgallery.core.network.di.coreNetworkModule
import com.eldirohmanur.chicagoartgallery.data.di.appDataModule
import com.eldirohmanur.chicagoartgallery.domain.di.appDomainModule
import com.eldirohmanur.chicagoartgallery.presentation.di.appPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(coreNetworkModule + coreModule + appDataModule + appDomainModule + appPresentationModule)
        }
    }
}