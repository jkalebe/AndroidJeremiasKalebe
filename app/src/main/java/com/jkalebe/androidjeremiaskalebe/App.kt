package com.jkalebe.androidjeremiaskalebe

import android.app.Application
import com.jkalebe.androidjeremiaskalebe.core.viewModule
import com.jkalebe.androidjeremiaskalebe.core.networkModule
import com.jkalebe.androidjeremiaskalebe.core.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class App: Application() {

    override fun onCreate() {
        super.onCreate()
         startKoin()
    }

    protected open fun startKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                listOf(
                    repositoryModule,
                    viewModule,
                    networkModule
                )
            )
        }
    }
}