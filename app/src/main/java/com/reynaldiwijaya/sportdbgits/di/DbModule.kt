package com.reynaldiwijaya.sportdbgits.di

import com.reynaldiwijaya.sportdbgits.data.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {

    single { AppDatabase.getAppDatabase(androidApplication().applicationContext) }

    single {
        val appDatabase : AppDatabase = get()
        return@single appDatabase.footballClubDao()
    }
}