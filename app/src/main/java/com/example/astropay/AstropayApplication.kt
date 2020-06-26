package com.example.astropay

import android.app.Application
import com.example.astropay.koin.appModule
import com.example.astropay.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AstropayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@AstropayApplication)
            modules(modules = listOf(appModule, networkModule))
        }
    }

}