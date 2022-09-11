package com.example.todo_list

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    lateinit var repository: ToDoRepository
    private lateinit var viewmodel : ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        viewmodel = ViewModelProvider(this).get(ToDoViewModel::class.java)

        val title = findViewById<EditText>(R.id.title)
        val content = findViewById<EditText>(R.id.content)
        val ratingbar = findViewById<RatingBar>(R.id.ratingBar)

        /**
         * Spinner (카테고리) 설정
         */
        val category = findViewById<Spinner>(R.id.category)
        var category_adapter = ArrayAdapter.createFromResource(this,
                                                        R.array.categoryName, android.R.layout.simple_spinner_item)

        category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        category.adapter = category_adapter


        /**
         * DatePicker 사용 / 날짜 선택 시 TextView에 해당 날짜 입력
         */
        val startdate = findViewById<TextView>(R.id.start_date)
        val deadlinedate = findViewById<TextView>(R.id.deadline_date)

        val startimage = findViewById<ImageView>(R.id.start_image)
        startimage.setOnClickListener{
            Datepicker(startdate)
        }

        val deadlineimage = findViewById<ImageView>(R.id.deadline_image)
        deadlineimage.setOnClickListener{
            Datepicker(deadlinedate)
        }


        // 취소 버튼
        val cancel = findViewById<Button>(R.id.cancel_button)
        cancel.setOnClickListener{ finish() }

        // 등록 버튼
        val register = findViewById<Button>(R.id.register_button)
        register.setOnClickListener{

            val newTodo = ToDoEntity(
                category = category.selectedItem.toString(),
                title = title.text.toString(),
                content = content.text.toString(),
                start_date = startdate.text.toString(),
                deadline_date = deadlinedate.text.toString(),
                importance = ratingbar.rating,
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