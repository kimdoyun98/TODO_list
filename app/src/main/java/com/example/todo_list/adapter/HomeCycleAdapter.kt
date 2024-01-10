package com.example.todo_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.HomeViewModel
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.databinding.RecyclerviewHomeItemBinding

class HomeCycleAdapter(val context: Context, val viewModel: HomeViewModel) : RecyclerView.Adapter<HomeCycleAdapter.MyViewHolder>() {
    private lateinit var binding : RecyclerviewHomeItemBinding
    private var list : MutableList<CycleEntity> = mutableListOf()

    // Controller
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){

        fun bind(cycleEntity: CycleEntity){
            binding.homeEntity = cycleEntity
        }
    }

    // 여기서 set 설정
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: CycleEntity = list[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RecyclerviewHomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<CycleEntity>){
        list.let {
            this.list.run {
                clear()
                addAll(list)
                notifyDataSetChanged()
            }
        }
    }
}