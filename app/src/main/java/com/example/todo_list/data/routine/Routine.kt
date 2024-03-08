package com.example.todo_list.data.routine

import androidx.lifecycle.LiveData
import com.example.todo_list.data.RoutineEntity

interface Routine {
    suspend fun selectAll(): List<RoutineEntity>
    suspend fun setAlarm(title: String): Int
    suspend fun update()
    suspend fun todaySuccess(id:Int)
    suspend fun insert (routineEntity: RoutineEntity)
    suspend fun delete(id : Int): Int
}