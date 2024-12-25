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
    }
    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}
