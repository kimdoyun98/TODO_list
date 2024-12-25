package com.example.todo_list.adapter.routine

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.R
import com.example.todo_list.alarm.Alarm
import com.example.todo_list.data.room.RoutineEntity
import com.example.todo_list.databinding.RecyclerviewCycleItemBinding
import com.example.todo_list.ui.routine.RoutineViewModel

class RoutineAdapter(
    val context: Context,
    val viewModel: RoutineViewModel,
    val alarm: Alarm
) : RecyclerView.Adapter<RoutineAdapter.MyViewHolder>() {
    private lateinit var binding: RecyclerviewCycleItemBinding
    private var list: MutableList<RoutineEntity> = mutableListOf()

    // Controller
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {
            binding.root.setOnClickListener {
                val pos: Int = adapterPosition

                val builder = AlertDialog.Builder(context)
                builder.setTitle(context.getString(R.string.routine_delete_dialog_title))
                    .setMessage(list[pos].title)
                builder.setPositiveButton(context.getString(R.string.dialog_positive_text)) { _, _ ->
                    viewModel.delete(list[pos].id)
                    alarm.cancelAlarm(list[pos].id)
                }
                builder.setNegativeButton(context.getString(R.string.dialog_negative_text)) { _, _ ->

                }
                builder.show()
            }
        }

        fun bind(cycleEntity: RoutineEntity) {
            binding.cycleEntity = cycleEntity
        }
    }

    // 여기서 set 설정
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: RoutineEntity = list[position]
        holder.bind(data)

        /**
         * 선택한 요일 색 표시
         */
        val content = context.getString(R.string.days)
        val spannableString = SpannableString(content)

        val saveIndex = ArrayList<Int>()
        for (i in 0..6) {
            if (data.day?.get(i)!!) saveIndex.add(i)
        }

        if (saveIndex.size == 7) {
            binding.showDay.text = context.getString(R.string.daily)
            binding.showDay.setTextColor(Color.BLUE)
        } else {
            for (i in saveIndex) {
                val index = when (i) {
                    0 -> 14
                    1 -> 2
                    2 -> 4
                    3 -> 6
                    4 -> 8
                    5 -> 10
                    else -> 12
                }

                val colorSpan = ForegroundColorSpan(Color.BLUE)
                spannableString.setSpan(
                    colorSpan,
                    index - 1,
                    index,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.showDay.text = spannableString
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            RecyclerviewCycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<RoutineEntity>) {
        list.let {
            this.list.run {
                clear()
                addAll(list)
                notifyDataSetChanged()
            }
        }
    }
}
