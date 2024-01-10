package com.example.todo_list.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todo_list.common.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoRepository(application: Application) {
    private val todoDataBase = ToDoDataBase.getInstance(application)!!
    private val todoDao = todoDataBase.todoDao()
    private var listAll : LiveData<List<ToDoEntity>> = todoDao.getAll()

    companion object{
        private var sInstance: ToDoRepository? = null
        fun getInstance(): ToDoRepository {
            return sInstance
                ?: synchronized(this){
                    val instance = ToDoRepository(MyApplication.instance)
                    sInstance = instance
                    instance
                }
        }
    }

    fun selectAll (): LiveData<List<ToDoEntity>> {
        return this.listAll
    }

    fun selectOnDate(date : String?) : LiveData<List<ToDoEntity>>{
        return todoDao.getCalumOnDate(date)
    }

    fun delete(id : Int) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                todoDao.delete(id)
            }
        }
        catch (e: Exception){
        }
    }

    fun insert (toDoEntity: ToDoEntity){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                todoDao.insert(toDoEntity)
            }
        }
        catch (e: Exception){
        }
    }

    fun update (toDoEntity: ToDoEntity){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                todoDao.update(toDoEntity)
            }
        }
        catch (e: Exception){
        }
    }

    fun success(id: Int){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                todoDao.success(id)
            }
        }
        catch (e: Exception){
        }
    }

}