package com.example.todo_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list.data.RoutineEntity
import com.example.todo_list.data.routine.RoutineRepository
import com.example.todo_list.data.ScheduleEntity
import com.example.todo_list.data.schedule.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor
                    (private val scheduleRepository: ScheduleRepository, private val routineRepository: RoutineRepository)
    : ViewModel() {
    private var calendar = Calendar.getInstance()
    private val today = MutableLiveData<Int>()
    private val day = calendar.get(Calendar.DAY_OF_WEEK)
    private val selectionFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    private val _routineAll = MutableLiveData<List<RoutineEntity>>()
    private val _scheduleAll = MutableLiveData<List<ScheduleEntity>>()

    init {
        today.value = day
    }

    fun getAll() : LiveData<List<RoutineEntity>>{
        viewModelScope.launch { _routineAll.value = routineRepository.selectAll() }
        return _routineAll
    }

    fun todoAll() : LiveData<List<ScheduleEntity>> {
        viewModelScope.launch { _scheduleAll.value =  scheduleRepository.selectAll()}
        return _scheduleAll
    }

    fun getDay() : MutableLiveData<Int> {
        return this.today
    }

    fun getDDay(deadline : String): String {
        val todayDate = calendar.time.time
        val endDate = SimpleDateFormat("yyyyMMdd").parse(deadline).time
        val today = selectionFormatter.format(LocalDate.now())

        return if (today == deadline) "D-Day"
               else if (today.toInt() > deadline.toInt()) "기간 지남"
               else {
                   val D_Day = (endDate - todayDate) / (24 * 60 * 60 * 1000) + 1
                   "D-$D_Day"
               }
    }
}