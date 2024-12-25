package com.example.todo_list.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
        lateinit var prefs: PreferenceUtil
//        fun applicationContext(): Context {
//            return instance.applicationContext
//        }
//        fun getRepository(): ToDoRepository{
//            return ToDoRepository.getInstance()
//        }
    }
    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}