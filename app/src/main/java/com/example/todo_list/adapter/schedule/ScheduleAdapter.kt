package com.example.todo_list.adapter.schedule

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.ui.schedule.ScheduleViewModel
import com.example.todo_list.ui.schedule.DetailActivity
import com.example.todo_list.adapter.schedule.ScheduleAdapter.MyViewHolder
import com.example.todo_list.adapter.DiffUtilCallBackTODO
import com.example.todo_list.data.room.ScheduleEntity
import com.example.todo_list.databinding.RecyclerviewTodoItemBinding

class ScheduleAdapter(val context: Context, val viewModel: ScheduleViewModel) : RecyclerView.Adapter<MyViewHolder>(){
    private lateinit var binding : RecyclerviewTodoItemBinding
    private var list : MutableList<ScheduleEntity> = mutableListOf()

    // Controller
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){
        init {
            binding.root.setOnClickListener{
                var pos : Int = adapterPosition
                val item : Array<String> = arrayOf("수정", "삭제", "완료")

                val builder = AlertDialog.Builder(context)
                builder.setItems(item) { _, which ->
                    when (which) {
                        0 -> { // 상세
                            val intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra("data", list[pos])
                            v.context.startActivity(intent)
                        }
                        1 -> { // 삭제
                            viewModel.delete(list[pos].id)
                        }
                        2 -> { // 완료
                            viewModel.success(list[pos].id)
                        }
                    }
                }
                builder.show()
            }
        }

        fun bind(toDoEntity: ScheduleEntity){
            binding.todoEntity = toDoEntity
        }
    }

    // 여기서 set 설정
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: ScheduleEntity = list[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RecyclerviewTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<ScheduleEntity>){
        list.let {
            val diffCallback = DiffUtilCallBackTODO(this.list, list)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            this.list.run {
                clear()
                addAll(list)
                diffResult.dispatchUpdatesTo(this@ScheduleAdapter)
            }
        }
    }

}
