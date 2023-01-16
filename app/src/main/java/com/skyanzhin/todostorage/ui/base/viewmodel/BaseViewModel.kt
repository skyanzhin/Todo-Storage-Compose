package com.skyanzhin.todostorage.ui.base.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {
    protected val isLoading = MutableStateFlow(false)
    protected val userMessage = MutableStateFlow<@receiver:StringRes Int?>(null)

    protected abstract fun <T> handleError(error: T)
}