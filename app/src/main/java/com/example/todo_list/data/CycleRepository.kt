package com.example.todo_list.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todo_list.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CycleRepository(application: Application) {
    private val todoDataBase = ToDoDataBase.getInstance(application)!!
    private val cycleDao = todoDataBase.cycleDao()
    private val cycleAll :LiveData<List<CycleEntity>> = cycleDao.getAll()

    companion object{
        private var sInstance: CycleRepository? = null
        fun getInstance(): CycleRepository {
            return sInstance
                ?: synchronized(this){
                    val instance = CycleRepository(MyApplication.instance)
                    sInstance = instance
                    instance
                }
        }
    }

    fun selectAll() : LiveData<List<CycleEntity>>{
        return this.cycleAll
    }

    fun update() {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                cycleDao.update()
            }
        }
        catch (e: Exception){
        }
    }

    fun todaySuccess(id:Int) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                cycleDao.todaySuccess(id)
            }
        }
        catch (e: Exception){
        }
    }

    fun insert (cycleEntity: CycleEntity){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                cycleDao.insert(cycleEntity)
            }
        }
        catch (e: Exception){
        }
    }

    fun delete(id : Int) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                cycleDao.delete(id)
            }
        }
        catch (e: Exception){
        }
    }
}