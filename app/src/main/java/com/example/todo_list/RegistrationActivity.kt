package com.example.todo_list

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository
import com.example.todo_list.databinding.ActivityRegistrationBinding
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
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
            Datepicker(binding.startDate)
        }

        binding.deadlineImage.setOnClickListener{
            Datepicker(binding.deadlineDate)
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

            finish()
        }
    }

    fun Datepicker(textView: TextView){
        val datePicker = DatePickerDialog(this, {_, year, month, day ->
            var a = ""
            var b = ""
            if((month + 1) < 10) a = "0"
            if (day < 10) b = "0"
            textView.text =
                year.toString() + "${a}" + (month + 1).toString() + "${b}" + day.toString()
        }, year, month, day)
        datePicker.show()
    }
}