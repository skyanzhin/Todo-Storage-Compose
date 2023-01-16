package com.skyanzhin.todostorage.ui.features.todo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.skyanzhin.todostorage.R
import com.skyanzhin.todostorage.data.errors.TodoListExceptions
import com.skyanzhin.todostorage.domain.base.Result
import com.skyanzhin.todostorage.domain.todo.repository.TodoRepository
import com.skyanzhin.todostorage.ui.base.viewmodel.BaseViewModel
import com.skyanzhin.todostorage.ui.common.Async
import com.skyanzhin.todostorage.ui.common.filterByType
import com.skyanzhin.todostorage.ui.features.todo.model.TasksFilterType
import com.skyanzhin.todostorage.ui.features.todo.model.TodoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TodoListUiState(
    val todos: List<TodoUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)

@HiltViewModel
class TodoListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    todoRepository: TodoRepository
) : BaseViewModel() {

    private val savedFilterType =
        savedStateHandle.getStateFlow(KEY_TASKS_FILTER_SAVED_STATE, TasksFilterType.ALL)

    private val filteredTodos =
        combine(todoRepository.getTodosFlow(), savedFilterType) { todosResult, type ->
            when (todosResult) {
                is Result.Success -> {
                    todosResult.data.filterByType(type)
                }
                is Result.Error -> {
                    //TODO: show error message
                    handleError(todosResult.error as? TodoListExceptions)
                    listOf()
                }
            }
        }
            .map { Async.Success(it) }
            .onStart<Async<List<TodoUiModel>>> { emit(Async.Loading) }

    val uiState: StateFlow<TodoListUiState> = combine(
        filteredTodos, userMessage, isLoading
    ) { asyncTodos, message, isLoading ->
        when (asyncTodos) {
            Async.Loading -> {
                TodoListUiState(isLoading = true)
            }
            is Async.Success -> {
                TodoListUiState(
                    todos = asyncTodos.data,
                    isLoading = isLoading,
                    userMessage = message
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = TodoListUiState(isLoading = true)
    )

    override fun <T> handleError(error: T) {
        viewModelScope.launch {
            userMessage.emit(
                when (error) {
                    TodoListExceptions.EmptyListException -> R.string.empty_list_error
                    else -> R.string.something_went_wrong_error
                }
            )
        }
    }
}

private const val KEY_TASKS_FILTER_SAVED_STATE = "KEY_TASKS_FILTER_SAVED_STATE"