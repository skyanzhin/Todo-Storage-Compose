package com.skyanzhin.todostorage.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skyanzhin.todostorage.data.local.dao.TodoDao
import com.skyanzhin.todostorage.data.local.model.TodoLocalModel

@Database(
    entities = [
        TodoLocalModel::class
    ],
    version = DATABASE_VERSION
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "todo_storage_compose.db"