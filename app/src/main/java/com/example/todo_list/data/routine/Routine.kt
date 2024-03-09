package com.example.todo_list.data.routine

import androidx.lifecycle.LiveData
import com.example.todo_list.data.RoutineEntity
import kotlinx.coroutines.flow.Flow

interface Routine {
    fun selectAll(): Flow<List<RoutineEntity>>
    suspend fun getId(title: String): Int
    suspend fun update()
    suspend fun todaySuccess(id:Int)
    suspend fun insert (routineEntity: RoutineEntity)
    suspend fun delete(id : Int): Int
}