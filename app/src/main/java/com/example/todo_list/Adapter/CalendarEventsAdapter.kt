package com.example.todo_list.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.data.CalendarEvent
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.databinding.EventItemViewBinding
/**
 * com.kizitonwose.calendarview.CalendarView
 */
class CalendarEventsAdapter (val onClick: (CalendarEvent) -> Unit) :
    RecyclerView.Adapter<CalendarEventsAdapter.Example3EventsViewHolder>() {

    private lateinit var binding : EventItemViewBinding
    var list : MutableList<ToDoEntity> = mutableListOf()
    //val events = mutableListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Example3EventsViewHolder {
        binding = EventItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Example3EventsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: Example3EventsViewHolder, position: Int) {
        val data: ToDoEntity = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

    inner class Example3EventsViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {

//        init {
//            itemView.setOnClickListener {
//                onClick(events[bindingAdapterPosition])
//            }
//        }

        fun bind(toDoEntity: ToDoEntity){
            binding.todoEntity = toDoEntity
        }
    }
}