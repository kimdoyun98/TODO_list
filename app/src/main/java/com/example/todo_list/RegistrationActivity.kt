package com.example.todo_list

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todo_list.Common.DatePicker
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository
import com.example.todo_list.databinding.ActivityRegistrationBinding
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var viewmodel : ToDoViewModel
    private lateinit var binding : ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this).get(ToDoViewModel::class.java)

        /**
         * Spinner (카테고리) 설정
         */
        //val category = findViewById<Spinner>(R.id.category)
        var category_adapter = ArrayAdapter.createFromResource(this,
                                                        R.array.categoryName, android.R.layout.simple_spinner_item)

        category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.category.adapter = category_adapter


        /**
         * DatePicker 사용 / 날짜 선택 시 TextView에 해당 날짜 입력
         */
        binding.startImage.setOnClickListener{
            DatePicker(RegistrationActivity@this, binding.startDate)
        }

        binding.deadlineImage.setOnClickListener{
            DatePicker(RegistrationActivity@this, binding.deadlineDate)
        }

        // 취소 버튼
        binding.cancelButton.setOnClickListener{ finish() }

        // 등록 버튼
        binding.registerButton.setOnClickListener{

            val newTodo = ToDoEntity(
                category = binding.category.selectedItem.toString(),
                title = binding.title.text.toString(),
                content = binding.content.text.toString(),
                start_date = binding.startDate.text.toString(),
                deadline_date = binding.deadlineDate.text.toString(),
                importance = binding.ratingBar.rating,
                success = false
            )
            viewmodel.insert(newTodo)
            Log.e("RegistrationActivity", "등록")
            finish()
        }
    }
}