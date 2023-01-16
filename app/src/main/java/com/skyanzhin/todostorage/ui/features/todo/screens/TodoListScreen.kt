package com.skyanzhin.todostorage.ui.features.todo.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skyanzhin.todostorage.ui.features.todo.model.TodoUiModel
import com.skyanzhin.todostorage.ui.features.todo.viewmodel.TodoListViewModel

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.padding(it)) {
            items(uiState.todos) { todo ->
                TodoItem(todoUiModel = todo)
            }
        }
    }
}

@Composable
fun TodoItem(todoUiModel: TodoUiModel) {
    Card(
        Modifier
            .fillMaxWidth(0.8f)
            .clickable { },
        shape = CardDefaults.outlinedShape
    ) {
        Text(text = todoUiModel.text)
    }
}