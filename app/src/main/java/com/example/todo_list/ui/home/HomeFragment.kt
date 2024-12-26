package com.example.todo_list.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todo_list.adapter.home.HomeRoutineAdapter
import com.example.todo_list.adapter.home.HomeScheduleAdapter
import com.example.todo_list.data.room.RoutineEntity
import com.example.todo_list.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var todayData: MutableList<RoutineEntity> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * 오늘 할 일
         */
        val adapter = HomeRoutineAdapter()
        val todayRecyclerView = binding.todayRecyclerview
        todayRecyclerView.adapter = adapter

        viewModel.getDay().observe(viewLifecycleOwner) {
            viewModel.getRoutineAll.observe(viewLifecycleOwner) { dataList ->
                todayData.clear()
                for (data in dataList) {
                    if (data.day?.get(it - 1) == true) todayData.add(data)
                }
                adapter.submitList(todayData.toList())
            }
        }
        /**
         * 일정
         */
        val personalAdapter = HomeScheduleAdapter(viewModel)
        val personalRecyclerView = binding.personalRecyclerview
        personalRecyclerView.adapter = personalAdapter

        viewModel.getScheduleAll.observe(viewLifecycleOwner) { dataList ->
            personalAdapter.submitList(dataList)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
