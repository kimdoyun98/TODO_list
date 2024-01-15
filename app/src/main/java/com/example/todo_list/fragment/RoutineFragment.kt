package com.example.todo_list.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todo_list.activity.CycleRegisterActivity
import com.example.todo_list.adapter.CycleAdapter
import com.example.todo_list.CycleViewModel
import com.example.todo_list.databinding.FragmentRoutineBinding

class RoutineFragment :Fragment(){
    private lateinit var binding : FragmentRoutineBinding
    private val viewModel: CycleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoutineBinding.bind(view)

        val adapter = CycleAdapter(requireContext(), viewModel)
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter

        viewModel.getAll().observe(viewLifecycleOwner){ data ->
            Log.e("data", data.toString())
            adapter.setData(data)
        }

        /***
         * 추가 버튼
         */
        binding.addButton.setOnClickListener{
            val intent = Intent(context, CycleRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentRoutineBinding.inflate(layoutInflater).root
    }
}