package com.example.todo_list.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.Activity.TodoRegisterActivity
import com.example.todo_list.Adapter.TodoAdapter
import com.example.todo_list.R
import com.example.todo_list.ToDoViewModel
import com.example.todo_list.databinding.FragmentTodoBinding

class ToDoFragment : BaseFragment(R.layout.fragment_todo) {
    private lateinit var binding : FragmentTodoBinding
    private val viewModel : ToDoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTodoBinding.bind(view)
        binding.todoViewModel = viewModel

        homeActivityToolbar.title = "Schedule"

        val adapter = TodoAdapter(requireContext(), viewModel)
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAll().observe(viewLifecycleOwner) { list ->
            // 정렬
            viewModel.sortFilter.observe(viewLifecycleOwner) { filter ->
                when(filter) {
                    ToDoViewModel.LATEST -> adapter.setData(list.sortedByDescending { it.id })
                    ToDoViewModel.DEADLINE -> adapter.setData(list.sortedByDescending { it.deadline_date}.reversed())
                }
            }
        }

        /***
         * 추가 버튼
         */
        binding.addButton.setOnClickListener{
            val intent = Intent(context, TodoRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}