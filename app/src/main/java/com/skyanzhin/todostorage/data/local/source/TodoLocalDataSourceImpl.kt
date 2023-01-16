package com.skyanzhin.todostorage.data.local.source

import com.skyanzhin.todostorage.data.TodoDataSource
import com.skyanzhin.todostorage.data.errors.TodoListExceptions
import com.skyanzhin.todostorage.data.local.dao.TodoDao
import com.skyanzhin.todostorage.data.local.model.TodoLocalModel
import com.skyanzhin.todostorage.domain.base.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TodoLocalDataSourceImpl(
    private val todoDao: TodoDao,
    private val ioDispatcher: CoroutineDispatcher
) : TodoDataSource {
    override fun getTodosFlow(): Flow<Result<List<TodoLocalModel>>> {
        return todoDao.getTodosFlow().map {
            if (it.isEmpty()) Result.Error(TodoListExceptions.EmptyListException)
            else Result.Success(it)
        }
    }

    override fun getTodo(id: Long): Flow<Result<TodoLocalModel>> {
        return todoDao.getTodo(id).map {
            if (it == null) Result.Error(TodoListExceptions.NoSuchTodoException)
            else Result.Success(it)
        }
    }

    override suspend fun createTodo(todoLocalModel: TodoLocalModel) = withContext(ioDispatcher) {
        todoDao.insert(todoLocalModel)
    }

    override suspend fun updateTodo(todoLocalModel: TodoLocalModel) = withContext(ioDispatcher) {
        todoDao.update(todoLocalModel)
    }

    override suspend fun deleteTodo(todoLocalModel: TodoLocalModel) = withContext(ioDispatcher) {
        todoDao.delete(todoLocalModel)
    }

    override suspend fun deleteTodos(todos: List<TodoLocalModel>) = withContext(ioDispatcher) {
        todoDao.delete(todos)
    }
}