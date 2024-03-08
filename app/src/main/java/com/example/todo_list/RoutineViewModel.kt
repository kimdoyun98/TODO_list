package com.example.todo_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list.data.RoutineEntity
import com.example.todo_list.data.routine.RoutineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(private val repository: RoutineRepository) : ViewModel() {
    private val _selectAll = MutableLiveData<List<RoutineEntity>>()
    var selectAll: LiveData<List<RoutineEntity>> = _selectAll

    private val _alarmCode = MutableLiveData<Int>()
    fun getAll() = viewModelScope.launch { _selectAll.value = repository.selectAll() }

    fun setAlarm(title: String):LiveData<Int>{
        viewModelScope.launch { _alarmCode.value = repository.setAlarm(title) }
        return _alarmCode
    }

    fun update() = viewModelScope.launch{ repository.update() }

    fun todaySuccess(id:Int) = viewModelScope.launch { repository.todaySuccess(id) }

    fun insert(routineEntity : RoutineEntity) = viewModelScope.launch { repository.insert(routineEntity) }

    fun delete(id : Int) = viewModelScope.launch{ repository.delete(id) }
}