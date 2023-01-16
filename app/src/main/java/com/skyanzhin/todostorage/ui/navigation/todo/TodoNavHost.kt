package com.skyanzhin.todostorage.ui.navigation.todo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skyanzhin.todostorage.ui.features.todo.screens.TodoListScreen
import com.skyanzhin.todostorage.ui.navigation.Screen

@Composable
fun TodoNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.TodoList
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(Screen.TodoList.route) {
            TodoListScreen()
        }
    }
}