package com.kumar.test.application

import android.app.Application
import com.kumar.test.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    companion object {
        lateinit var appInstance: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    restRepositoryModule,
                    restApiModule,
                    retrofitModule,
                    domainModule,
                    viewModelModule
                )
            )
        }
        appInstance = this
    }
}