package com.example.todo_list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : ToDoRepository = ToDoRepository.getInstance()
    var sortFilter: MutableLiveData<Int> = MutableLiveData<Int>(1)

    fun getAll() : LiveData<List<ToDoEntity>> {
        return repository.selectAll()
    }

    fun getPersonal() : LiveData<List<ToDoEntity>>{
        return repository.selectPersonal()
    }

    fun getProject() : LiveData<List<ToDoEntity>>{
        return repository.selectProject()
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
    }

    fun onClickSetFilterDEADLINE() {
        sortFilter.value = DEADLINE
    }

    fun onClickSetFilterRATING() {
        sortFilter.value = RATING
    }

    companion object {
        const val LATEST = 1
        const val DEADLINE = 2
        const val RATING = 3
    }

}