package com.skyanzhin.todostorage.data

import com.skyanzhin.todostorage.data.local.model.TodoLocalModel
import com.skyanzhin.todostorage.domain.base.Result
import kotlinx.coroutines.flow.Flow

interface TodoDataSource {
    fun getTodosFlow(): Flow<Result<List<TodoLocalModel>>>
    fun getTodo(id: Long): Flow<Result<TodoLocalModel>>
    suspend fun createTodo(todoLocalModel: TodoLocalModel)
    suspend fun updateTodo(todoLocalModel: TodoLocalModel)
    suspend fun deleteTodo(todoLocalModel: TodoLocalModel)
    suspend fun deleteTodos(todos: List<TodoLocalModel>)
}