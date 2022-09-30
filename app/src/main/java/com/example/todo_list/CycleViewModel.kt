package com.example.todo_list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.data.CycleRepository
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*

class CycleViewModel(application: Application) : AndroidViewModel(application) {
    private val cycleRepository : CycleRepository = CycleRepository.getInstance()

    fun getAll() : LiveData<List<CycleEntity>>{
        return cycleRepository.selectAll()
    }

    fun update() {
        cycleRepository.update()
    }

    fun todaySuccess(id:Int) {
        Log.d("todaySuccess", id.toString())
        cycleRepository.todaySuccess(id)
    }

    fun insert(cycleEntity : CycleEntity) {
        cycleRepository.insert(cycleEntity)
    }

    fun delete(id : Int){
        cycleRepository.delete(id)
    }
}