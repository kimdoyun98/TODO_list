package com.example.todo_list.Activity

import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.todo_list.CycleViewModel
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.databinding.ActivityCycleRegisterBinding

class CycleRegisterActivity : AppCompatActivity() {
    private val viewModel : CycleViewModel by viewModels()
    private lateinit var binding : ActivityCycleRegisterBinding
    private var checkedDayList = MutableList<Boolean>(7) { _ -> false}
    private lateinit var text : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCycleRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sunday.setOnCheckedChangeListener(DayToggle())
        binding.monday.setOnCheckedChangeListener(DayToggle())
        binding.tuesday.setOnCheckedChangeListener(DayToggle())
        binding.wednesday.setOnCheckedChangeListener(DayToggle())
        binding.thursday.setOnCheckedChangeListener(DayToggle())
        binding.friday.setOnCheckedChangeListener(DayToggle())
        binding.saturday.setOnCheckedChangeListener(DayToggle())

        binding.cycleCancel.setOnClickListener{
            finish()
        }

        binding.cycleRegister.setOnClickListener{
            val newEntity = CycleEntity(
                title  = binding.title.text.toString(),
                day = checkedDayList,
                success = false
            )
            viewModel.insert(newEntity)
            finish()
        }
    }
    inner class DayToggle: CompoundButton.OnCheckedChangeListener{
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