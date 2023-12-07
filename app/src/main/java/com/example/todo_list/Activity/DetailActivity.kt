package com.example.todo_list.Activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todo_list.Common.DatePicker
import com.example.todo_list.R
import com.example.todo_list.ToDoViewModel
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.databinding.ActivityRegisterBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializableExtra("data") as ToDoEntity

        val categoryAdapter = ArrayAdapter.createFromResource(this,
            R.array.categoryName, android.R.layout.simple_spinner_item)

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.todoEntity = data

        binding.calendar.setOnClickListener{
            DatePicker(this, binding.startDate)
        }

        binding.cancelButton.setOnClickListener{ finish() }
        binding.registerButton.setOnClickListener {
            val viewModel = ViewModelProvider(this)[ToDoViewModel::class.java]

            viewModel.update(
                ToDoEntity(
                    id = data.id,
                    title = binding.title.text.toString(),
                    content = binding.content.text.toString(),
                    start_date = binding.startDate.text.toString(),
                    deadline_date = binding.endDate.text.toString(),
                    success = false
                )
            )
            finish()
        }
    }
}