package com.example.todo_list.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.Adapter.RecyclerviewAdapter.MyViewHolder
import com.example.todo_list.Common.DiffUtilCallBack
import com.example.todo_list.DetailActivity
import com.example.todo_list.ToDoViewModel
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.databinding.RecyclerviewBinding

class RecyclerviewAdapter(val context: Context, val viewModel: ToDoViewModel) : RecyclerView.Adapter<MyViewHolder>(){
    private lateinit var binding : RecyclerviewBinding
    private var list : MutableList<ToDoEntity> = mutableListOf()

    // Controller
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){
        init {
            binding.root.setOnClickListener(View.OnClickListener {
                var pos : Int = adapterPosition
                val item : Array<String> = arrayOf("상세", "삭제", "완료")

                val builder = AlertDialog.Builder(context)
                builder.setItems(item,
                    DialogInterface.OnClickListener { dialog, which ->
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
                    })
                builder.show()
            })
        }

        fun bind(toDoEntity: ToDoEntity){
            binding.todoEntity = toDoEntity
        }
    }

    // 여기서 set 설정
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: ToDoEntity = list[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<ToDoEntity>){
        list?.let {
            val diffCallback = DiffUtilCallBack(this.list, list)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            this.list.run {
                clear()
                addAll(list)
                diffResult.dispatchUpdatesTo(this@RecyclerviewAdapter)
            }
        }
    }

}
