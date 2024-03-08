package com.example.todo_list.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScheduleDAO {
    @Query("SELECT * FROM scheduleEntity WHERE success = :suc")
    suspend fun getAll(suc:Boolean = false): List<ScheduleEntity>

    @Query("SELECT * FROM scheduleEntity WHERE start_date <= :date AND deadline_date >= :date AND success = :suc")
    suspend fun getCalumOnDate(date:String?, suc: Boolean = false) : List<ScheduleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(toDoEntity: ScheduleEntity)

    @Query("Delete From scheduleEntity WHERE id = :id")
    suspend fun delete(id : Int): Int

    @Update
    suspend fun update(toDoEntity: ScheduleEntity)

    @Query("Update scheduleEntity SET success = :suc WHERE id = :id")
    suspend fun success(id : Int, suc : Boolean = true)
}

@Dao
interface RoutineDAO {
    @Query("SELECT * FROM routineEntity")
    suspend fun getAll(): List<RoutineEntity>

    @Query("SELECT id FROM routineEntity WHERE title =:title")
    suspend fun getId(title: String): Int

    @Insert
    suspend fun insert(routineEntity: RoutineEntity)

    @Query("UPDATE RoutineEntity SET success =:suc")
    suspend fun update(suc: Boolean = false)

    @Query("UPDATE RoutineEntity SET success =:suc WHERE id =:id")
    suspend fun todaySuccess(id: Int, suc: Boolean = true)

    @Query("Delete From RoutineEntity WHERE id = :id")
    suspend fun delete(id : Int): Int
}
