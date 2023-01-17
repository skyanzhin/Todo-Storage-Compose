package com.skyanzhin.todostorage.di.todo

import android.content.Context
import androidx.room.Room
import com.skyanzhin.todostorage.data.TodoDataSource
import com.skyanzhin.todostorage.data.local.dao.TodoDao
import com.skyanzhin.todostorage.data.local.database.DATABASE_NAME
import com.skyanzhin.todostorage.data.local.database.TodoDatabase
import com.skyanzhin.todostorage.data.local.source.TodoLocalDataSourceImpl
import com.skyanzhin.todostorage.data.repository.TodoRepositoryImpl
import com.skyanzhin.todostorage.di.base.IoDispatcher
import com.skyanzhin.todostorage.domain.todo.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TodoDataModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(
        @ApplicationContext context: Context
    ): TodoDatabase = Room.databaseBuilder(context, TodoDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao = todoDatabase.todoDao()

    @Provides
    @Singleton
    fun provideTodoDataSource(
        todoDao: TodoDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): TodoDataSource = TodoLocalDataSourceImpl(todoDao, ioDispatcher)

    @Provides
    @Singleton
    fun provideTodoRepository(
        todoDataSource: TodoDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): TodoRepository = TodoRepositoryImpl(todoDataSource, ioDispatcher)
}