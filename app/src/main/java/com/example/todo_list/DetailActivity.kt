package com.example.todo_list

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.databinding.ActivityRegistrationBinding

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

        binding.registerButton.setOnClickListener {
            //TODO 업데이트
        }
    }
}