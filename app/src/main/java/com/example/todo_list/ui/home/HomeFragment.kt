package com.example.todo_list.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.todo_list.adapter.home.HomeRoutineAdapter
import com.example.todo_list.adapter.home.HomeScheduleAdapter
import com.example.todo_list.data.room.RoutineEntity
import com.example.todo_list.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 오늘 할 일
        val adapter = HomeRoutineAdapter()
        val todayRecyclerView = binding.todayRecyclerview
        todayRecyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getRoutineAll.collect{ routineData ->
                    adapter.submitList(routineData.toList())
                }
            }
        }

        // 일정
        val personalAdapter = HomeScheduleAdapter()
        val personalRecyclerView = binding.personalRecyclerview
        personalRecyclerView.adapter = personalAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getScheduleAll.collect{ scheduleData ->
                    personalAdapter.submitList(scheduleData)
                }
            }
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
