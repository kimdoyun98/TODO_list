package com.example.todo_list.Adapter

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
import com.example.todo_list.CycleViewModel
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.databinding.RecyclerviewCycleItemBinding


class CycleAdapter (val context: Context, val viewModel: CycleViewModel) : RecyclerView.Adapter<CycleAdapter.MyViewHolder>() {
    private lateinit var binding : RecyclerviewCycleItemBinding
    private var list : MutableList<CycleEntity> = mutableListOf()

    // Controller
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){
        init {
            binding.root.setOnClickListener(View.OnClickListener {
                var pos : Int = adapterPosition

                val builder = AlertDialog.Builder(context)
                builder.setTitle("다음 내용을 삭제하시겠습니까?").setMessage(list[pos].title)
                builder.setPositiveButton("확인") { _, _ ->
                    viewModel.delete(list[pos].id)
                }
                builder.setNegativeButton("취소") {_,_ ->

                }
                builder.show()
            })
        }

        fun bind(cycleEntity: CycleEntity){
            binding.cycleEntity = cycleEntity
        }
    }

    // 여기서 set 설정
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: CycleEntity = list[position]
        holder.bind(data)

        /**
         * 선택한 요일 색 표시
         */
        val content = " 월 화 수 목 금 토 일"
        val spannableString = SpannableString(content)

        var saveIndex = ArrayList<Int>()
        for ( i: Int in 0..6){
            if(data.day?.get(i) == true){
                saveIndex.add(i)
            }
        }

        if (saveIndex.size == 7) {
            binding.showDay.text = "매일"
            binding.showDay.setTextColor(Color.BLUE)
        }
        else{
            for(i:Int in saveIndex){
                var index : Int = 0
                when(i){
                    0 -> index = 0
                    1 -> index = 2
                    2 -> index = 4
                    3 -> index = 6
                    4 -> index = 8
                    5 -> index = 10
                    6 -> index = 12
                }

                val colorSpan = ForegroundColorSpan(Color.BLUE)
                spannableString.setSpan(colorSpan, index-1, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.showDay.text = spannableString
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RecyclerviewCycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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