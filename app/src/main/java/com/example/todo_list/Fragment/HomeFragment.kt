package com.example.todo_list.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.todo_list.MainActivity
import com.example.todo_list.R
import com.example.todo_list.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var binding : FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        /**
         * 앱 실행 시 MainActivity에서 binding을 초기화해주기 전에 HomeFragment 호출 -> homeActivityToolbar 실행
         * homeActivityToolbar가 실행 되었을 때 MainActivity의 binding이 초기화 되지 않은 상태이므로
         * UninitializedPropertyAccessException 등 여러 오류발생
         * 따라서 다음과 같이 조치함
         */
        try { homeActivityToolbar.title = "Home" }
        catch (e : Exception){}

    }
}