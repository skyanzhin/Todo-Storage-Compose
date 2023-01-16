package com.skyanzhin.todostorage.domain.todo.repository

import com.skyanzhin.todostorage.domain.todo.model.TodoDomainModel
import com.skyanzhin.todostorage.domain.base.Result
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodosFlow(): Flow<Result<List<TodoDomainModel>>>
    fun getTodo(todoId: Long): Flow<Result<TodoDomainModel>>
    suspend fun createTodo(todoDomainModel: TodoDomainModel)
    suspend fun updateTodo(todoDomainModel: TodoDomainModel)
    suspend fun deleteTodo(todoDomainModel: TodoDomainModel)
    suspend fun deleteTodos(todos: List<TodoDomainModel>)
}