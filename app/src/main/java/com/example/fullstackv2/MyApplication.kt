package com.example.fullstackv2

import android.app.Application
import com.example.fullstackv2.features.discover.discoverModule
import com.example.fullstackv2.local.localModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MyApplication)
            modules(listOf(
                networkModule,
                localModule,
                discoverModule,
            ))
        }
    }
}