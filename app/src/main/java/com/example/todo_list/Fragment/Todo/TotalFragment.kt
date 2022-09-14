package com.example.todo_list.Fragment.Todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.Adapter.RecyclerviewAdapter
import com.example.todo_list.ToDoViewModel
import com.example.todo_list.databinding.FragmentTabBinding

class TotalFragment : Fragment() {
    private lateinit var binding : FragmentTabBinding
    private val viewModel : ToDoViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabBinding.inflate(layoutInflater)

        val adapter = RecyclerviewAdapter(requireContext(), viewModel)
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAll().observe(viewLifecycleOwner) { list ->
            // 정렬
            viewModel.sortFilter.observe(viewLifecycleOwner) { filter ->
                when(filter) {
                    ToDoViewModel.LATEST -> adapter.setData(list.sortedByDescending { it.id })
                    ToDoViewModel.DEADLINE -> adapter.setData(list.sortedByDescending { it.deadline_date}.reversed())
                    ToDoViewModel.RATING -> adapter.setData(list.sortedByDescending { it.importance })
                }
            }
        }

        return binding.root
    }
}