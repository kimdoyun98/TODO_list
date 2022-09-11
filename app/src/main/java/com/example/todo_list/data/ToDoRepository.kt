package com.example.todo_list.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todo_list.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoRepository(application: Application) {
    private val todoDataBase = ToDoDataBase.getInstance(application)!!
    private val todoDao = todoDataBase.todoDao()
    private var category: String? = null
    private var list : LiveData<List<ToDoEntity>> = todoDao.getMatchCategoryAll()

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

    fun select (): LiveData<List<ToDoEntity>> {
        return this.list
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
}