package com.example.todo_list.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.todo_list.Adapter.HomeCycleAdapter
import com.example.todo_list.Adapter.HomeTodoAdapter
import com.example.todo_list.HomeViewModel
import com.example.todo_list.R
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()
    private var todayData : MutableList<CycleEntity> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        /**
         * 앱 실행 시 MainActivity에서 binding을 초기화해주기 전에 HomeFragment 호출 -> homeActivityToolbar 실행
         * homeActivityToolbar가 실행 되었을 때 MainActivity의 binding이 초기화 되지 않은 상태이므로
         * UninitializedPropertyAccessException 등 여러 오류발생
         * 따라서 다음과 같이 조치함
         */
        try { homeActivityToolbar.title = "정각에 해야지" }
        catch (e : Exception){}

        /**
         * 오늘 할 일
         */
        val adapter = HomeCycleAdapter(requireContext(), viewModel)
        val todayRecyclerView = binding.todayRecyclerview
        todayRecyclerView.adapter = adapter

        viewModel.getDay().observe(viewLifecycleOwner){
            viewModel.getAll().observe(viewLifecycleOwner) { dataList ->
                todayData.clear()
                for (data in dataList){
                    if(data.day?.get(it-1) == true) todayData.add(data)
                }
                adapter.setData(todayData)
            }
        }
        /**
         * 일정
         */
        val personalAdapter = HomeTodoAdapter(requireContext(), viewModel)
        val personalRecyclerView = binding.personalRecyclerview
        personalRecyclerView.adapter = personalAdapter

        viewModel.todoAll().observe(viewLifecycleOwner){ dataList ->
            personalAdapter.setData(dataList)
        }

    }
}