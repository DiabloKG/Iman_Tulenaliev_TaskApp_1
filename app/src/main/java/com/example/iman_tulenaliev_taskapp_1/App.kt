package com.example.iman_tulenaliev_taskapp_1

import android.app.Application
import androidx.room.Room
import com.example.iman_tulenaliev_taskapp_1.database.local.TaskDatabase


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            TaskDatabase::class.java,
            "database")
            .allowMainThreadQueries()
            .build()
    }

    companion object{
        lateinit var db: TaskDatabase
    }

}