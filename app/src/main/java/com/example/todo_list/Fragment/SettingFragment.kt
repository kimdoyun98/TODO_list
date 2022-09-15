package com.example.todo_list.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo_list.R
import com.example.todo_list.databinding.FragmentCalendarBinding
import com.example.todo_list.databinding.FragmentSettingBinding
import com.example.todo_list.databinding.FragmentTodoBinding

class SettingFragment : BaseFragment(R.layout.fragment_setting) {
    private lateinit var binding : FragmentSettingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingBinding.bind(view)

        homeActivityToolbar.title = "Setting"
    }
}