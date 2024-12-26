package com.example.todo_list.adapter.schedule

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.R
import com.example.todo_list.adapter.ItemDiffCallback
import com.example.todo_list.adapter.schedule.ScheduleAdapter.MyViewHolder
import com.example.todo_list.data.room.ScheduleEntity
import com.example.todo_list.databinding.RecyclerviewTodoItemBinding

class ScheduleAdapter(
    val context: Context,
    val onClickDetail: (ScheduleEntity) -> Unit,
    val onClickDelete: (Int) -> Unit,
    val onClickSuccess: (Int) -> Unit
) : ListAdapter<ScheduleEntity, MyViewHolder>(
    ItemDiffCallback(
        onItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    inner class MyViewHolder(
        private val binding: RecyclerviewTodoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val pos: Int = absoluteAdapterPosition
                val item = context.resources.getStringArray(R.array.ScheduleMenu)

                val builder = AlertDialog.Builder(context)
                builder.setItems(item) { _, which ->
                    when (which) {
                        0 -> onClickDetail(getItem(pos))

                        1 -> onClickDelete(getItem(pos).id)

                        2 -> onClickSuccess(getItem(pos).id)
                    }
                }
                builder.show()
            }
        }

        fun bind(toDoEntity: ScheduleEntity) {
            binding.todoEntity = toDoEntity
        }
    }

    // 여기서 set 설정
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerviewTodoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
