package com.example.todo_list.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM todoentity WHERE category = :category AND success = :suc")
    fun getMatchCategory(category:String?, suc:Boolean? = false): LiveData<List<ToDoEntity>>

    @Query("SELECT * FROM todoentity")
    fun getAll(): LiveData<List<ToDoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toDoEntity: ToDoEntity)

    @Query("Delete From todoentity WHERE id = :id")
    fun delete(id : Int): Int
}