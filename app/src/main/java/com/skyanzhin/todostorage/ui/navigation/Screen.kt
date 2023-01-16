package com.skyanzhin.todostorage.ui.navigation

sealed class Screen(val route: String) {
    object TodoList : Screen("TodoList")
}