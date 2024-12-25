package com.example.todo_list.data.repository.schedule

import com.example.todo_list.data.room.ScheduleEntity
import kotlinx.coroutines.flow.Flow

interface Schedule {
    fun selectAll(): Flow<List<ScheduleEntity>>
    fun selectOnDate(date : String?) : Flow<List<ScheduleEntity>>
    suspend fun insert(toDoEntity: ScheduleEntity)
    suspend fun delete(id : Int): Int
    suspend fun update(toDoEntity: ScheduleEntity)
    suspend fun success(id: Int)
}