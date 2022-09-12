package com.example.todo_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    val dataAll : LiveData<List<ToDoEntity>>
    val dataPersonal : LiveData<List<ToDoEntity>>
    private val repository : ToDoRepository = ToDoRepository.getInstance()

    init {
        dataAll = getAll()
        dataPersonal = getPersonal()
    }

    fun getAll() : LiveData<List<ToDoEntity>> {
        return repository.selectAll()
    }

    fun getPersonal() : LiveData<List<ToDoEntity>>{
        return repository.selectPersonal()
    }

    fun insert(toDoEntity: ToDoEntity){
        repository.insert(toDoEntity)
    }

    fun delete(id : Int){
        repository.delete(id)
    }

}