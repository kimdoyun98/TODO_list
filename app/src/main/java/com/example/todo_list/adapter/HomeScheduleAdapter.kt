package com.example.todo_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.HomeViewModel
import com.example.todo_list.data.ScheduleEntity
import com.example.todo_list.databinding.RecyclerviewHometodoItemBinding

class HomeScheduleAdapter (val context: Context, val viewModel: HomeViewModel) : RecyclerView.Adapter<HomeScheduleAdapter.MyViewHolder>() {
    private lateinit var binding : RecyclerviewHometodoItemBinding
    private var list : MutableList<ScheduleEntity> = mutableListOf()

    // Controller
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){

        fun bind(todoEntity: ScheduleEntity){
            binding.toDoEntity = todoEntity
            binding.viewModel = viewModel
        }
    }

    // 여기서 set 설정
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: ScheduleEntity = list[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RecyclerviewHometodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<ScheduleEntity>){
        list.let {

            this.list.run {
                clear()
                addAll(list)
                notifyDataSetChanged()
            }
        }
    }
}