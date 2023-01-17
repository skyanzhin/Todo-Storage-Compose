package com.skyanzhin.todostorage.common

import com.skyanzhin.todostorage.domain.todo.model.TodoDomainModel
import com.skyanzhin.todostorage.ui.features.todo.model.TasksFilterType
import com.skyanzhin.todostorage.ui.features.todo.model.TodoUiModel

fun List<TodoDomainModel>.filterByType(filterType: TasksFilterType): List<TodoUiModel> = filter {
    when (filterType) {
        TasksFilterType.ALL -> true
        TasksFilterType.ACTIVE -> !it.isCompleted
        TasksFilterType.COMPLETED -> it.isCompleted
    }
}.map { it.asUiModel() }