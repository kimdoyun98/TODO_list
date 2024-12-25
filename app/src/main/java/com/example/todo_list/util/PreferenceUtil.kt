package com.example.todo_list.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil (context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)

    fun getAlarm(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun setAlarm(key: String, boolean: Boolean) {
        prefs.edit().putBoolean(key, boolean).apply()
    }

    companion object{
        private const val SETTINGS = "settings"
        const val PUSH_ALARM = "pushAlarm"
    }
}
