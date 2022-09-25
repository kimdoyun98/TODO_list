package com.example.todo_list.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.Activity.CycleRegisterActivity
import com.example.todo_list.Activity.RegistrationActivity
import com.example.todo_list.Adapter.CycleAdapter
import com.example.todo_list.CycleViewModel
import com.example.todo_list.R
import com.example.todo_list.databinding.FragmentCycleBinding

class CycleFragment :BaseFragment(R.layout.fragment_cycle){
    private lateinit var binding : FragmentCycleBinding
    private val viewModel: CycleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCycleBinding.bind(view)

        homeActivityToolbar.title = "Cycle"

        val adapter = CycleAdapter(requireContext(), viewModel)
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAll().observe(viewLifecycleOwner){ data ->
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
}