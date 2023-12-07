package com.example.todo_list.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.todo_list.ToDoViewModel
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.databinding.ActivityRegisterBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class TodoRegisterActivity : AppCompatActivity() {
    private val viewModel : ToDoViewModel by viewModels()
    private lateinit var binding : ActivityRegisterBinding

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * DatePicker 사용 / 날짜 선택 시 TextView에 해당 날짜 입력
         */
        binding.calendar.setOnClickListener{
            val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("기간을 선택해 주세요.")
                .build()
            dateRangePicker.show(supportFragmentManager, "date picker")
            dateRangePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = it?.first ?: 0
                binding.startDate.text = SimpleDateFormat("yyyyMMdd").format(calendar.time).toString()

                calendar.timeInMillis = it?.second ?: 0
                binding.endDate.text = SimpleDateFormat("yyyyMMdd").format(calendar.time).toString()
            }
        }

        // 취소 버튼
        binding.cancelButton.setOnClickListener{ finish() }

        // 등록 버튼
        binding.registerButton.setOnClickListener{

            val newTodo = ToDoEntity(
                title = binding.title.text.toString(),
                content = binding.content.text.toString(),
                start_date = binding.startDate.text.toString(),
                deadline_date = binding.endDate.text.toString(),
                success = false
            )
            viewModel.insert(newTodo)
            finish()
        }
    }
}