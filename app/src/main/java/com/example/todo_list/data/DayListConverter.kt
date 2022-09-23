package com.example.todo_list.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class DayListConverter {
    @TypeConverter
    fun listToJson(value: List<Boolean>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Boolean>? {
        return Gson().fromJson(value,Array<Boolean>::class.java)?.toList()
    }
}