package com.skyanzhin.todostorage.domain.base

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Exception) : Result<Nothing>()

    val isSuccess get() = this is Success && data != null
}