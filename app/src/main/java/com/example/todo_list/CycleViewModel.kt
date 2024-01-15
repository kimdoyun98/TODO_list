package com.example.todo_list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.data.CycleRepository

class CycleViewModel(application: Application) : AndroidViewModel(application) {
    private val cycleRepository : CycleRepository = CycleRepository.getInstance()

    fun getAll() : LiveData<List<CycleEntity>>{
        return cycleRepository.selectAll()
    }

    fun setAlarm(title: String):LiveData<Int>{
        return cycleRepository.setAlarm(title)
    }

    fun update() {
        cycleRepository.update()
    }

    fun todaySuccess(id:Int) {
        cycleRepository.todaySuccess(id)
    }

    fun insert(cycleEntity : CycleEntity) {
        cycleRepository.insert(cycleEntity)
    }

    fun delete(id : Int){
        cycleRepository.delete(id)
    }
}