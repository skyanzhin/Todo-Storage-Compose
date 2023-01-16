package com.skyanzhin.todostorage.data.local.model

import androidx.room.Entity

@Entity(tableName = "todo_local_model")
data class TodoLocalModel(
    val id: Long,
    val text: String,
    val isCompleted: Boolean
)