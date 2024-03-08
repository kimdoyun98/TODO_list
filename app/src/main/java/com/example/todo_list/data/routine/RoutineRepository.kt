package com.example.todo_list.data.routine

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todo_list.common.MyApplication
import com.example.todo_list.data.DataBase
import com.example.todo_list.data.RoutineDAO
import com.example.todo_list.data.RoutineEntity
import javax.inject.Inject

class RoutineRepository @Inject constructor(private val routineDAO: RoutineDAO): Routine {
    override suspend fun selectAll() : List<RoutineEntity> = routineDAO.getAll()

    override suspend fun setAlarm(title: String):Int = routineDAO.getId(title)

    override suspend fun update() = routineDAO.update()

    override suspend fun todaySuccess(id:Int) = routineDAO.todaySuccess(id)

    override suspend fun insert (routineEntity: RoutineEntity) = routineDAO.insert(routineEntity)

    override suspend fun delete(id : Int) = routineDAO.delete(id)
}