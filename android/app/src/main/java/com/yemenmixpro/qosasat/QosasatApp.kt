package com.yemenmixpro.qosasat

import android.app.Application
import androidx.room.Room

class QosasatApp : Application() {
    companion object {
        lateinit var database: com.yemenmixpro.qosasat.data.AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, com.yemenmixpro.qosasat.data.AppDatabase::class.java, "qosasat.db").build()
    }
}
