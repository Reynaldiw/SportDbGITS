package com.reynaldiwijaya.sportdbgits.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reynaldiwijaya.sportdbgits.data.footballclub.local.FootballClubDao
import com.reynaldiwijaya.sportdbgits.data.footballclub.model.TeamItem

@Database(entities = [TeamItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE : AppDatabase? = null

        fun getAppDatabase(context : Context) : AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "teams.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun footballClubDao() : FootballClubDao
}