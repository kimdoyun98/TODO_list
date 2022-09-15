package com.example.todo_list

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : ToDoRepository = ToDoRepository.getInstance()
    var sortFilter: MutableLiveData<Int> = MutableLiveData<Int>(1)

    // Data Binding 을 위한 Boolean 변수들 (TextStyle, TextColor)
    var isSortedByLatest: ObservableField<Boolean> = ObservableField<Boolean>(true)
    var isSortedByDeadline: ObservableField<Boolean> = ObservableField<Boolean>(false)
    var isSortedByRating: ObservableField<Boolean> = ObservableField<Boolean>(false)

    fun getAll() : LiveData<List<ToDoEntity>> {
        return repository.selectAll()
    }

    fun getPersonal() : LiveData<List<ToDoEntity>>{
        return repository.selectPersonal()
    }

    fun getProject() : LiveData<List<ToDoEntity>>{
        return repository.selectProject()
    }

    fun getOnDate(date : String?) : LiveData<List<ToDoEntity>>{
        return repository.selectOnDate(date)
    }

    fun insert(toDoEntity: ToDoEntity){
        repository.insert(toDoEntity)
    }

    fun delete(id : Int){
        repository.delete(id)
    }

    fun update(toDoEntity: ToDoEntity){
        repository.update(toDoEntity)
    }

    fun success(id : Int){
        repository.success(id)
    }


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