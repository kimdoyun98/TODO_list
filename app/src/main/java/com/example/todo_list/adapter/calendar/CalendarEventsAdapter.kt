package com.example.todo_list.adapter.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.adapter.ItemDiffCallback
import com.example.todo_list.data.room.ScheduleEntity
import com.example.todo_list.databinding.EventItemViewBinding

/**
 * com.kizitonwose.calendarview.CalendarView
 */
class CalendarEventsAdapter
    : ListAdapter<ScheduleEntity, CalendarEventsAdapter.CalendarEventsAdapterViewHolder>(
    ItemDiffCallback(
        onItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    inner class CalendarEventsAdapterViewHolder(
        private val binding: EventItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(toDoEntity: ScheduleEntity) {
            binding.todoEntity = toDoEntity
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarEventsAdapterViewHolder {
        return CalendarEventsAdapterViewHolder(
            EventItemViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CalendarEventsAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
