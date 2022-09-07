package com.example.todo_list

import android.app.Application
import android.content.Context
import com.example.todo_list.data.ToDoRepository

class MyApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }
        fun getRepository(): ToDoRepository{
            return ToDoRepository.getInstance()
        }
    }
    override fun onCreate() {
        super.onCreate()
    }
}