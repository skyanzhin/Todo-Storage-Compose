package com.skyanzhin.todostorage.ui.common

import com.skyanzhin.todostorage.data.local.model.TodoLocalModel
import com.skyanzhin.todostorage.domain.todo.model.TodoDomainModel
import com.skyanzhin.todostorage.ui.features.todo.model.TodoUiModel

fun TodoDomainModel.asUiModel() = TodoUiModel(
    id = id,
    text = text,
    isCompleted = isCompleted
)

fun TodoUiModel.asDomainModel() = TodoDomainModel(
    id = id,
    text = text,
    isCompleted = isCompleted
)

fun TodoDomainModel.asLocalModel() = TodoLocalModel(
    id = id,
    text = text,
    isCompleted = isCompleted
)

fun TodoLocalModel.asDomainModel() = TodoDomainModel(
    id = id,
    text = text,
    isCompleted = isCompleted
)