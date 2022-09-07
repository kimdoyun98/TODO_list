package com.example.todo_list.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String?,
    val title: String?,
    val content: String?,
    val start_date: String?,
    val deadline_date: String?,
    val importance: Float?,
    @ColumnInfo(defaultValue = "false") val success: Boolean?


)
