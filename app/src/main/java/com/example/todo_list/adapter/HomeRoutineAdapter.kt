package com.example.todo_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.ui.HomeViewModel
import com.example.todo_list.data.RoutineEntity
import com.example.todo_list.databinding.RecyclerviewHomeItemBinding

class HomeRoutineAdapter(val context: Context, val viewModel: HomeViewModel) : RecyclerView.Adapter<HomeRoutineAdapter.MyViewHolder>() {
    private lateinit var binding : RecyclerviewHomeItemBinding
    private var list : MutableList<RoutineEntity> = mutableListOf()

    // Controller
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){

        fun bind(cycleEntity: RoutineEntity){
            binding.homeEntity = cycleEntity
        }
    }

    // 여기서 set 설정
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: RoutineEntity = list[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RecyclerviewHomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<RoutineEntity>){
        list.let {
            this.list.run {
                clear()
                addAll(list)
                notifyDataSetChanged()
            }
        }
    }
}