package com.skyanzhin.todostorage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.skyanzhin.todostorage.ui.features.todo.screens.TodoListScreen
import com.skyanzhin.todostorage.ui.navigation.todo.TodoNavHost
import com.skyanzhin.todostorage.ui.theme.TodoStorageComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoStorageComposeTheme {
                window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoNavHost()
                }
            }
        }
    }
}