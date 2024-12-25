package com.example.todo_list.ui.routine

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_list.data.repository.routine.RoutineRepository
import com.example.todo_list.data.room.RoutineEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(
    private val repository: RoutineRepository
) : ViewModel() {
    val getAll: LiveData<List<RoutineEntity>> = repository.selectAll().asLiveData()

    private val _id = MutableStateFlow<Int>(-1)
    val id: StateFlow<Int> = _id
    fun getId(title: String): StateFlow<Int> {
        viewModelScope.launch { _id.emit(repository.getId(title)) }
        return id
    }

    fun update() = viewModelScope.launch { repository.update() }

    fun todaySuccess(id: Int) = viewModelScope.launch { repository.todaySuccess(id) }

    fun insert(routineEntity: RoutineEntity) =
        viewModelScope.launch { repository.insert(routineEntity) }

    fun delete(id: Int) = viewModelScope.launch { repository.delete(id) }
}
