package com.skyanzhin.todostorage.data.repository

import com.skyanzhin.todostorage.data.TodoDataSource
import com.skyanzhin.todostorage.data.errors.TodoListExceptions
import com.skyanzhin.todostorage.domain.base.Result
import com.skyanzhin.todostorage.domain.todo.model.TodoDomainModel
import com.skyanzhin.todostorage.domain.todo.repository.TodoRepository
import com.skyanzhin.todostorage.ui.common.asDomainModel
import com.skyanzhin.todostorage.ui.common.asLocalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TodoRepositoryImpl(
    private val todoDataSource: TodoDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : TodoRepository {
    override fun getTodosFlow(): Flow<Result<List<TodoDomainModel>>> {
        return todoDataSource.getTodosFlow().map { result ->
            when (result) {
                is Result.Success -> Result.Success(result.data.map { it.asDomainModel() })
                is Result.Error -> Result.Error(result.error)
            }
        }
    }

    override fun getTodo(todoId: Long): Flow<Result<TodoDomainModel>> {
        return todoDataSource.getTodo(todoId).map { result ->
            when (result) {
                is Result.Success -> Result.Success(result.data.asDomainModel())
                is Result.Error -> Result.Error(result.error)
            }
        }
    }

    override suspend fun createTodo(todoDomainModel: TodoDomainModel) = withContext(ioDispatcher) {
        todoDataSource.createTodo(todoDomainModel.asLocalModel())
    }

    override suspend fun updateTodo(todoDomainModel: TodoDomainModel) = withContext(ioDispatcher) {
        todoDataSource.updateTodo(todoDomainModel.asLocalModel())
    }

    override suspend fun deleteTodo(todoDomainModel: TodoDomainModel) = withContext(ioDispatcher) {
        todoDataSource.deleteTodo(todoDomainModel.asLocalModel())
    }

    override suspend fun deleteTodos(todos: List<TodoDomainModel>) = withContext(ioDispatcher) {
        todoDataSource.deleteTodos(todos.map { it.asLocalModel() })
    }
}