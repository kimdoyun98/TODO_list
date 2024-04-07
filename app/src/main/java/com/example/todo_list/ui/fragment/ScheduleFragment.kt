package com.example.todo_list.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.ui.ScheduleViewModel
import com.example.todo_list.ui.activity.ScheduleRegisterActivity
import com.example.todo_list.adapter.ScheduleAdapter
import com.example.todo_list.databinding.FragmentScheduleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment() {
    private lateinit var binding : FragmentScheduleBinding
    private val viewModel : ScheduleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.todoViewModel = viewModel

        val adapter = ScheduleAdapter(requireContext(), viewModel)
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAll.observe(viewLifecycleOwner) { list ->
            // 정렬
            viewModel.sortFilter.observe(viewLifecycleOwner) { filter ->
                when(filter) {
                    ScheduleViewModel.LATEST -> adapter.setData(list.sortedByDescending { it.id })
                    ScheduleViewModel.DEADLINE -> adapter.setData(list.sortedByDescending { it.deadline_date}.reversed())
                }
            }
        }

        /***
         * 추가 버튼
         */
        binding.addButton.setOnClickListener{
            val intent = Intent(context, ScheduleRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentScheduleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}