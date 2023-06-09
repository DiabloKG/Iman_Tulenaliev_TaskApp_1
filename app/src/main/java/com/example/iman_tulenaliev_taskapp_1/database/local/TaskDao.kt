package com.example.iman_tulenaliev_taskapp_1.database.local

import androidx.room.*
import com.example.iman_tulenaliev_taskapp_1.ui.home.TaskModel

@Dao
interface TaskDao {
    @Insert
    fun insert(task: TaskModel)

    @Update
    fun update(task: TaskModel)

    @Delete
    fun deleteTask(task: TaskModel)

    @Query("SELECT * FROM TaskModel")
    fun getAllTask(): List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY id DESC")
    fun getAllTaskByDate(): List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY title ASC ")
    fun getAllTaskByAlphabetAZ(): List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY title DESC ")
    fun getAllTaskByAlphabetZA(): List<TaskModel>


}