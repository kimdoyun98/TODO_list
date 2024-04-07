package com.example.todo_list.data.schedule

import com.example.todo_list.data.ScheduleDAO
import com.example.todo_list.data.ScheduleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepository @Inject constructor(private val scheduleDAO: ScheduleDAO): Schedule {
    override fun selectAll(): Flow<List<ScheduleEntity>> = scheduleDAO.getAll()

    override fun selectOnDate(date : String?) : Flow<List<ScheduleEntity>> = scheduleDAO.getCalumOnDate(date)

    override suspend fun delete(id : Int) = withContext(Dispatchers.IO) { scheduleDAO.delete(id) }

    override suspend fun insert (toDoEntity: ScheduleEntity) = withContext(Dispatchers.IO) { scheduleDAO.insert(toDoEntity) }

    override suspend fun update (toDoEntity: ScheduleEntity) = withContext(Dispatchers.IO){ scheduleDAO.update(toDoEntity) }

    override suspend fun success(id: Int) = withContext(Dispatchers.IO){ scheduleDAO.success(id) }

}