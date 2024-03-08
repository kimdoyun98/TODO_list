package com.example.todo_list.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todo_list.activity.RoutineRegisterActivity
import com.example.todo_list.adapter.RoutineAdapter
import com.example.todo_list.RoutineViewModel
import com.example.todo_list.databinding.FragmentRoutineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutineFragment :Fragment(){
    private lateinit var binding : FragmentRoutineBinding
    private val viewModel: RoutineViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoutineBinding.bind(view)

        val adapter = RoutineAdapter(requireContext(), viewModel)
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter

        viewModel.selectAll.observe(viewLifecycleOwner){ data ->
            adapter.setData(data)
        }

        /***
         * 추가 버튼
         */
        binding.addButton.setOnClickListener{
            val intent = Intent(context, RoutineRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentRoutineBinding.inflate(layoutInflater).root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }
}