package com.example.todo_list.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.Adapter.PersonalAdapter
import com.example.todo_list.MyApplication
import com.example.todo_list.RegistrationActivity
import com.example.todo_list.ToDoViewModel
import com.example.todo_list.data.ToDoDataBase
import com.example.todo_list.data.ToDoEntity
import com.example.todo_list.data.ToDoRepository
import com.example.todo_list.databinding.FragmentPersonalBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonalFragment : Fragment() {
    private lateinit var binding : FragmentPersonalBinding
    private lateinit var viewModel: ToDoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalBinding.inflate(layoutInflater)


        /**
         * use LiveData & ViewModel
         */
        viewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        val adapter = PersonalAdapter(requireContext(), viewModel)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.dataPersonal.observe(viewLifecycleOwner, Observer { list ->
            adapter.setData(list)
        })


        /***
         * 추가 버튼
         */
        binding.addButton.setOnClickListener{
            val intent = Intent(context, RegistrationActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}