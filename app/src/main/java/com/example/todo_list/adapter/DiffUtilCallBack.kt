package com.example.todo_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todo_list.data.room.ScheduleEntity

class DiffUtilCallBackTODO(private val oldList: List<ScheduleEntity>, private val newList: List<ScheduleEntity>)
    : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id==newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==newList[newItemPosition]
    }
}