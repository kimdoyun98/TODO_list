package com.example.todo_list.data.schedule

import com.example.todo_list.data.ScheduleEntity

interface Schedule {
    suspend fun selectAll(): List<ScheduleEntity>
    suspend fun selectOnDate(date : String?) : List<ScheduleEntity>
    suspend fun insert(toDoEntity: ScheduleEntity)
    suspend fun delete(id : Int): Int
    suspend fun update(toDoEntity: ScheduleEntity)
    suspend fun success(id: Int)
}