package com.example.todo_list.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.Adapter.PersonalAdapter
import com.example.todo_list.MyApplication
import com.example.todo_list.R
import com.example.todo_list.RegistrationActivity
import com.example.todo_list.data.ToDoDB
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonalFragment : Fragment() {
    lateinit var repository: ToDoRepository
    lateinit var database : ToDoDB
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_personal, container, false)
        val add_button = view.findViewById<Button>(R.id.add_button)
        /***
         * repository using
         */
//        repository = ToDoRepository.getInstance()
//        var list = repository.select("개인")
//
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
//        var personalAdapter = PersonalAdapter(MyApplication.instance, list)
//
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        recyclerView.adapter = personalAdapter

        /***
         * Not use repository
         */
        database = ToDoDB.getInstance(MyApplication.instance)!!
        recyclerView.layoutManager = LinearLayoutManager(activity)
        select(null)

        /***
         * 추가 버튼
         */
        add_button.setOnClickListener{
            val intent = Intent(context, RegistrationActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    fun select (category: String?){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val list = database.todoDao().getMatchCategoryAll()
                val personalAdapter = PersonalAdapter(MyApplication.instance, list)
                recyclerView.adapter = personalAdapter
            }
        }
        catch (e: Exception){
        }
    }

    fun insert (toDoEntity: ToDoEntity){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                database.todoDao().insert(toDoEntity)
            }
        }
        catch (e: Exception){
        }
    }
}