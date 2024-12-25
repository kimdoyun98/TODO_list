package com.example.todo_list.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String?,
    val content: String?,
    val start_date: String?,
    val deadline_date: String?,
    @ColumnInfo(defaultValue = "false") val success: Boolean?
): Serializable

@Entity
data class RoutineEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String?,
    val day : List<Boolean>?,
    val success: Boolean?,
    val time : String
)