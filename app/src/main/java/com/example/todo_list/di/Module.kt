package com.example.todo_list.di

import android.content.Context
import com.example.todo_list.data.DataBase
import com.example.todo_list.data.RoutineDAO
import com.example.todo_list.data.ScheduleDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): DataBase = DataBase.getInstance(context)


    @Singleton
    @Provides
    fun provideScheduleDao(database: DataBase
    ): ScheduleDAO = database.scheduleDao

    @Singleton
    @Provides
    fun provideRoutineDAO(database: DataBase
    ): RoutineDAO = database.routineDao
}