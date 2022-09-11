package com.example.todo_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    val data : LiveData<List<ToDoEntity>>
    private val repository : ToDoRepository = ToDoRepository.getInstance()

    init {
        data = getAll()
    }

    fun getAll() : LiveData<List<ToDoEntity>> {
        return repository.select()
    }

    fun insert(toDoEntity: ToDoEntity){
        repository.insert(toDoEntity)
    }

}