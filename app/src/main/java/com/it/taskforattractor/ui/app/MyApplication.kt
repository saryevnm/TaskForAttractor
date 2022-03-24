package com.it.taskforattractor.ui.app

import android.app.Application
import android.content.Context
import com.it.taskforattractor.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    companion object {
        var appContext: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(koinModules)
        }
    }
}