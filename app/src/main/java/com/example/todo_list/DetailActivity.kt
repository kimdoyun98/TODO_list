package com.example.todo_list

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todo_list.Common.DatePicker
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.databinding.ActivityRegistrationBinding
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializableExtra("data") as ToDoEntity

        var category_adapter = ArrayAdapter.createFromResource(this,
            R.array.categoryName, android.R.layout.simple_spinner_item)

        category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.category.adapter = category_adapter
        when (data.category){
            "개인" -> binding.category.setSelection(0)
            "프로젝트" -> binding.category.setSelection(1)
        }

        binding.todoEntity = data

        binding.startImage.setOnClickListener{
            DatePicker(DetailActivity@this, binding.startDate)
        }

        binding.deadlineImage.setOnClickListener{
            DatePicker(DetailActivity@this, binding.deadlineDate)
        }

        binding.cancelButton.setOnClickListener{ finish() }
        binding.registerButton.setOnClickListener {
            val viewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
            val UpdateTodo = ToDoEntity(
                id = data.id,
                category = binding.category.selectedItem.toString(),
                title = binding.title.text.toString(),
                content = binding.content.text.toString(),
                start_date = binding.startDate.text.toString(),
                deadline_date = binding.deadlineDate.text.toString(),
                importance = binding.ratingBar.rating,
                success = false
            )
            Log.d("Update", UpdateTodo.toString())
            viewModel.update(UpdateTodo)
            finish()
        }
    }
}