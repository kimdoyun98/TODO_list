package com.example.todo_list.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.todo_list.Adapter.ViewpagerAdapter
import com.example.todo_list.RegistrationActivity
import com.example.todo_list.ToDoViewModel
import com.example.todo_list.databinding.FragmentTodoBinding
import com.google.android.material.tabs.TabLayoutMediator

class ToDoFragment : Fragment() {
    private lateinit var binding : FragmentTodoBinding
    private val viewModel : ToDoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoBinding.inflate(layoutInflater)
        binding.todoViewModel = viewModel


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
            val intent = Intent(context, RegistrationActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}