package com.example.iman_tulenaliev_taskapp_1.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iman_tulenaliev_taskapp_1.ui.home.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
abstract fun dao(): TaskDao
}