package com.example.todo_list.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.CycleViewModel
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.databinding.RecyclerviewHomeItemBinding

class HomeAdapter(val context: Context, val viewModel: CycleViewModel) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private lateinit var binding : RecyclerviewHomeItemBinding
    private var list : MutableList<CycleEntity> = mutableListOf()

    // Controller
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){
        init {
//            binding.successButton.setOnClickListener{
//                Log.e("CycleAdapter", "Click button")
//                var pos : Int = adapterPosition
//                viewModel.todaySuccess(list[pos].id)
//                list.clear()
//            }
        }

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
        list?.let {
//            val diffCallback = DiffUtilCallBack(this.list, list)
//            val diffResult = DiffUtil.calculateDiff(diffCallback)

            this.list.run {
                clear()
                addAll(list)
                notifyDataSetChanged()
            }
        }
    }

}