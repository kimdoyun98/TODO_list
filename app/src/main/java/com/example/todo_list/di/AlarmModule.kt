package com.example.todo_list.di

import android.content.Context
import com.example.todo_list.alarm.Alarm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlarmModule {

    @Singleton
    @Provides
    fun providerAlarm(
        @ApplicationContext context: Context
    ): Alarm = Alarm(context)

}
