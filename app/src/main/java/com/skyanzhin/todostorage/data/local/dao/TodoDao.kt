package com.skyanzhin.todostorage.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.skyanzhin.todostorage.data.local.model.TodoLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_local_model")
    fun getTodosFlow(): Flow<List<TodoLocalModel>>

    @Query("SELECT * FROM todo_local_model WHERE id=:todoId")
    fun getTodo(todoId: Long): Flow<TodoLocalModel?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoLocalModel: TodoLocalModel)

    @Update
    suspend fun update(todoLocalModel: TodoLocalModel)

    @Delete
    suspend fun delete(todoLocalModel: TodoLocalModel)

    @Delete
    suspend fun delete(todos:List<TodoLocalModel>)
}