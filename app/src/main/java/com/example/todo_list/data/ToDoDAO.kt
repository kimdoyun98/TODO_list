package com.example.todo_list.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDAO {
//    @Query("SELECT *FROM todoentity WHERE category MATCH :category AND success MATCH :suc")
//    fun getMatchCategoryAll(category:String?, suc:Boolean? = false): List<ToDoEntity>
    @Query("SELECT * FROM todoentity")
    fun getMatchCategoryAll(): List<ToDoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toDoEntity: ToDoEntity)

    @Delete
    fun delete(toDoEntity: ToDoEntity)
}