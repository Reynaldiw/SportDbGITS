package com.reynaldiwijaya.sportdbgits

import android.app.Application
import com.reynaldiwijaya.sportdbgits.di.apiModule
import com.reynaldiwijaya.sportdbgits.di.dbModule
import com.reynaldiwijaya.sportdbgits.di.teamModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class SportApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SportApplication)
            modules(listOf(
                apiModule, dbModule, teamModule
            ))
        }
    }
}