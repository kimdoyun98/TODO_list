package com.example.todo_list.data

import android.annotation.SuppressLint
import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.todo_list.Adapter.PersonalAdapter
import com.example.todo_list.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ToDoRepository(application: Application) {
    private val todoDB = ToDoDB.getInstance(application)!!
    private val todoDao = todoDB.todoDao()
    private var category: String? = null
    private var list : List<ToDoEntity>? = null

    init {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                list = todoDao.getMatchCategoryAll()
                Log.e("try", list.toString())
            }
        }
        catch (e: Exception){
        }
    }

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

    fun select (category: String): List<ToDoEntity>? {
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