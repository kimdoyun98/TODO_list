package com.example.todo_list.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String?,
    val content: String?,
    val start_date: String?,
    val deadline_date: String?,
    @ColumnInfo(defaultValue = "false") val success: Boolean?
): Serializable

@Entity
data class CycleEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String?,
    val day : List<Boolean>?,
    val success: Boolean?,
    val time : String
)