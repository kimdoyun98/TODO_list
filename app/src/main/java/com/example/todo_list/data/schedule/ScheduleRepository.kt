package com.example.todo_list.data.schedule

import com.example.todo_list.data.ScheduleDAO
import com.example.todo_list.data.ScheduleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleRepository @Inject constructor(private val scheduleDAO: ScheduleDAO): Schedule {
    override fun selectAll(): Flow<List<ScheduleEntity>> = scheduleDAO.getAll()

    override fun selectOnDate(date : String?) : Flow<List<ScheduleEntity>> = scheduleDAO.getCalumOnDate(date)

    override suspend fun delete(id : Int) = scheduleDAO.delete(id)

    override suspend fun insert (toDoEntity: ScheduleEntity) = scheduleDAO.insert(toDoEntity)

    override suspend fun update (toDoEntity: ScheduleEntity) = scheduleDAO.update(toDoEntity)

    override suspend fun success(id: Int) = scheduleDAO.success(id)

}