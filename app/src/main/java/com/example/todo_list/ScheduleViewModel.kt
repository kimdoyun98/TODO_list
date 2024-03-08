package com.example.todo_list

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list.data.ScheduleEntity
import com.example.todo_list.data.schedule.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repository: ScheduleRepository): ViewModel() {
    private val _scheduleAllData = MutableLiveData<List<ScheduleEntity>>()
    val scheduleAllData : LiveData<List<ScheduleEntity>> = _scheduleAllData

    private val _selectOnData = MutableLiveData<List<ScheduleEntity>>()
    //private val selectOnData : LiveData<List<ScheduleEntity>> = _selectOnData

    var sortFilter: MutableLiveData<Int> = MutableLiveData<Int>(1)

    // Data Binding 을 위한 Boolean 변수들 (TextStyle, TextColor)
    var isSortedByLatest: ObservableField<Boolean> = ObservableField<Boolean>(true)
    var isSortedByDeadline: ObservableField<Boolean> = ObservableField<Boolean>(false)
    var isSortedByRating: ObservableField<Boolean> = ObservableField<Boolean>(false)
    fun getAll() = viewModelScope.launch { _scheduleAllData.value = repository.selectAll() }

    fun getOnDate(date : String?) : LiveData<List<ScheduleEntity>>{
        viewModelScope.launch { _selectOnData.value = repository.selectOnDate(date) }
        return _selectOnData
    }

    fun insert(scheduleEntity: ScheduleEntity) = viewModelScope.launch{ repository.insert(scheduleEntity) }

    fun delete(id : Int) = viewModelScope.launch{ repository.delete(id) }

    fun update(scheduleEntity: ScheduleEntity) = viewModelScope.launch{ repository.update(scheduleEntity) }

    fun success(id : Int) = viewModelScope.launch{ repository.success(id) }


    fun onClickSetFilterLATEST() {
        sortFilter.value = LATEST

        isSortedByLatest.set(true)
        isSortedByDeadline.set(false)
        isSortedByRating.set(false)
    }

    fun onClickSetFilterDEADLINE() {
        sortFilter.value = DEADLINE

        isSortedByLatest.set(false)
        isSortedByDeadline.set(true)
        isSortedByRating.set(false)
    }

    fun onClickSetFilterRATING() {
        sortFilter.value = RATING

        isSortedByLatest.set(false)
        isSortedByDeadline.set(false)
        isSortedByRating.set(true)
    }

    companion object {
        const val LATEST = 1
        const val DEADLINE = 2
        const val RATING = 3
    }

}