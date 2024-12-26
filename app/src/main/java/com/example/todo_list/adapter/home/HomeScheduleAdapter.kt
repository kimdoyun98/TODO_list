package com.example.todo_list.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.adapter.ItemDiffCallback
import com.example.todo_list.data.room.ScheduleEntity
import com.example.todo_list.databinding.RecyclerviewHometodoItemBinding

class HomeScheduleAdapter : ListAdapter<ScheduleEntity, HomeScheduleAdapter.HomeScheduleViewHolder>(
    ItemDiffCallback(
        onItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    inner class HomeScheduleViewHolder(
        private val binding: RecyclerviewHometodoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todoEntity: ScheduleEntity) {
            binding.toDoEntity = todoEntity
        }
    }

    override fun onBindViewHolder(holder: HomeScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScheduleViewHolder {
        return HomeScheduleViewHolder(
            RecyclerviewHometodoItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }
}
