package com.example.todo_list.Fragment

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.todo_list.MainActivity
import com.example.todo_list.R
import com.example.todo_list.makeGone
import com.example.todo_list.makeVisible

/**
 * com.kizitonwose.calendarview.CalendarView
 */
interface HasToolbar {
    val toolbar: Toolbar? // Return null to hide the toolbar
}

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    val homeActivityToolbar: Toolbar
        get() = (requireActivity() as MainActivity).binding.toolbar

    override fun onStart() {
        super.onStart()
        if (this is HasToolbar) {
            homeActivityToolbar.makeGone()
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        }
    }

    override fun onStop() {
        super.onStop()
        if (this is HasToolbar) {
            homeActivityToolbar.makeVisible()
            (requireActivity() as AppCompatActivity).setSupportActionBar(homeActivityToolbar)
        }
    }

}
