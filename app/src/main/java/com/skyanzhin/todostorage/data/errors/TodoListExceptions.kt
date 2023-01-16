package com.skyanzhin.todostorage.data.errors

sealed class TodoListExceptions : Exception() {
    object EmptyListException : TodoListExceptions()
    object NoSuchTodoException : TodoListExceptions()
}