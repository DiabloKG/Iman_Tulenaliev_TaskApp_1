package com.example.iman_tulenaliev_taskapp_1.ui.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var title: String? = null,
    var desc: String? = null
):java.io.Serializable
