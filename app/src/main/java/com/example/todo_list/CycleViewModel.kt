package com.example.todo_list

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.data.CycleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class CycleViewModel(application: Application) : AndroidViewModel(application) {
    private var calendar = Calendar.getInstance()
    private val repository : CycleRepository = CycleRepository.getInstance()
    private val today = MutableLiveData<Int>()
    private val day = calendar.get(Calendar.DAY_OF_WEEK)

    init {
        today.value = day
    }

    fun getAll() : LiveData<List<CycleEntity>>{
        return repository.selectAll()
    }

    fun getDay() : MutableLiveData<Int> {
        return this.today
    }

    fun update() {
        repository.update()
    }

    fun todaySuccess(id:Int) {
        Log.d("todaySuccess", id.toString())
        repository.todaySuccess(id)
    }

    fun insert(cycleEntity : CycleEntity) {
        repository.insert(cycleEntity)
    }
}