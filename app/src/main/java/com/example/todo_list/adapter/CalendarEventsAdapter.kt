package com.example.todo_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.databinding.EventItemViewBinding

/**
 * com.kizitonwose.calendarview.CalendarView
 */
class CalendarEventsAdapter: RecyclerView.Adapter<CalendarEventsAdapter.CalendarEventsAdapterViewHolder>() {

    private lateinit var binding : EventItemViewBinding
    var list : MutableList<ToDoEntity> = mutableListOf()

    inner class CalendarEventsAdapterViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {

        fun bind(toDoEntity: ToDoEntity){
            binding.todoEntity = toDoEntity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarEventsAdapterViewHolder {
        binding = EventItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarEventsAdapterViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CalendarEventsAdapterViewHolder, position: Int) {
        val data: ToDoEntity = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size
}