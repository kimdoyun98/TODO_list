package com.example.todo_list.Common

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import com.example.todo_list.data.ToDoEntity

class DiffUtilCallBack(private val oldList: List<ToDoEntity>, private val newList: List<ToDoEntity>)
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