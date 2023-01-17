package com.skyanzhin.todostorage.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_local_model")
data class TodoLocalModel(
    @PrimaryKey
    val id: Long,
    val text: String,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean
)