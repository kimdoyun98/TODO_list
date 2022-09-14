package com.example.todo_list.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo_list.databinding.FragmentCalenderBinding
import com.example.todo_list.databinding.FragmentTodoBinding

class CalenderFragment : Fragment() {
    private lateinit var binding : FragmentCalenderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalenderBinding.inflate(layoutInflater)

        return binding.root
    }
}