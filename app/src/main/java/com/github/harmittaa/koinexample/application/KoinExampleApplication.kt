package com.github.harmittaa.koinexample.application

import android.app.Application
import com.github.harmittaa.koinexample.fragment.fragmentModule
import com.github.harmittaa.koinexample.fragment.viewModelModule
import com.github.harmittaa.koinexample.persistence.prefModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinExampleApplication)
            modules(listOf(prefModule, fragmentModule, viewModelModule))
        }
    }
}