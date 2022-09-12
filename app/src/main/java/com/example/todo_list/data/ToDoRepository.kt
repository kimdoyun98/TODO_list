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
    private var listAll : LiveData<List<ToDoEntity>> = todoDao.getAll()
    private var listPersonal : LiveData<List<ToDoEntity>> = todoDao.getMatchCategory("개인")
    private var listProject : LiveData<List<ToDoEntity>> = todoDao.getMatchCategory("프로젝트")

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

    fun selectPersonal() : LiveData<List<ToDoEntity>>{
        return this.listPersonal
    }

    fun selectProject() : LiveData<List<ToDoEntity>>{
        return this.listProject
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