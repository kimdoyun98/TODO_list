package com.example.todo_list.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todo_list.Fragment.Todo.PersonalFragment
import com.example.todo_list.Fragment.Todo.ProjectFragment
import com.example.todo_list.Fragment.Todo.TotalFragment
import com.example.todo_list.ToDoViewModel

class ViewpagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private lateinit var fragment : Fragment

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val totalFragment = TotalFragment()
        val persoanlFragment = PersonalFragment()
        val projectFragment = ProjectFragment()

        when (position) {
            0 -> {
                fragment = totalFragment
            }
            1 -> {
                fragment = persoanlFragment
            }
            2 -> {
                fragment = projectFragment
            }
        }

        return fragment
    }
}