package com.example.todo_list.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.todo_list.Adapter.ViewpagerAdapter
import com.example.todo_list.R
import com.example.todo_list.Activity.TodoRegisterActivity
import com.example.todo_list.ToDoViewModel
import com.example.todo_list.databinding.FragmentTodoBinding
import com.google.android.material.tabs.TabLayoutMediator

class ToDoFragment : BaseFragment(R.layout.fragment_todo) {
    private lateinit var binding : FragmentTodoBinding
    private val viewModel : ToDoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTodoBinding.bind(view)
        binding.todoViewModel = viewModel

        homeActivityToolbar.title = "ToDo List"


        /**
         * Tab + ViewPager
         */
        val tabItem = arrayOf("전체", "개인", "프로젝트")
        binding.viewpager2.adapter = ViewpagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tablayout, binding.viewpager2){ tab, position ->
            tab.text = tabItem[position]
        }.attach()

        /***
         * 추가 버튼
         */
        binding.addButton.setOnClickListener{
            val intent = Intent(context, TodoRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}