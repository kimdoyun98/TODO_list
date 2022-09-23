package com.example.todo_list.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import com.example.todo_list.CycleViewModel
import com.example.todo_list.R
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.databinding.FragmentCycleBinding

class CycleFragment :BaseFragment(R.layout.fragment_cycle){
    private lateinit var binding : FragmentCycleBinding
    private val viewModel: CycleViewModel by viewModels()
    private var checkedDayList = MutableList<Boolean>(7) { _ -> false}
    private lateinit var text : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCycleBinding.bind(view)

        binding.sunday.setOnCheckedChangeListener(Test())
        binding.monday.setOnCheckedChangeListener(Test())
        binding.tuesday.setOnCheckedChangeListener(Test())
        binding.wednesday.setOnCheckedChangeListener(Test())
        binding.thursday.setOnCheckedChangeListener(Test())
        binding.friday.setOnCheckedChangeListener(Test())
        binding.saturday.setOnCheckedChangeListener(Test())

        binding.cycleCancel.setOnClickListener{
            Log.d("Cycle Cancel", checkedDayList.toString())
        }

        binding.cycleRegister.setOnClickListener{
            val newEntity = CycleEntity(
                title  = binding.title.text.toString(),
                day = checkedDayList,
                success = false
            )
            viewModel.insert(newEntity)
        }
    }

    inner class Test:CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            when(buttonView?.id){
                binding.sunday.id -> {
                    checkedDayList[0] = isChecked
                }
                binding.monday.id -> {
                    checkedDayList[1] = isChecked
                }
                binding.tuesday.id -> {
                    checkedDayList[2] = isChecked
                }
                binding.wednesday.id -> {
                    checkedDayList[3] = isChecked
                }
                binding.thursday.id -> {
                    checkedDayList[4] = isChecked
                }
                binding.friday.id -> {
                    checkedDayList[5] = isChecked
                }
                binding.saturday.id -> {
                    checkedDayList[6] = isChecked
                }
            }
            text = ""
            if (checkedDayList.count{ it } == 7) text = "매일"
            else {
                for (i in 0 until checkedDayList.size) {
                    if (checkedDayList[i]) {
                        when (i) {
                            0 -> text += "일 "
                            1 -> text += "월 "
                            2 -> text += "화 "
                            3 -> text += "수 "
                            4 -> text += "목 "
                            5 -> text += "금 "
                            6 -> text += "토 "
                        }
                    }
                }
            }
            binding.choiceDay.text = text
        }

    }
}