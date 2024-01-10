package com.example.todo_list.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.ToDoViewModel
import com.example.todo_list.activity.TodoRegisterActivity
import com.example.todo_list.adapter.TodoAdapter
import com.example.todo_list.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {
    private lateinit var binding : FragmentScheduleBinding
    private val viewModel : ToDoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.todoViewModel = viewModel

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentScheduleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}