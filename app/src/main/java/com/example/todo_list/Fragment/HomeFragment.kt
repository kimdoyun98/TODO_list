package com.example.todo_list.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.Adapter.HomeAdapter
import com.example.todo_list.CycleViewModel
import com.example.todo_list.R
import com.example.todo_list.data.CycleEntity
import com.example.todo_list.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : CycleViewModel by viewModels()
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

        val adapter = HomeAdapter(requireContext(), viewModel)
        val recyclerView = binding.todayRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getDay().observe(viewLifecycleOwner){

            viewModel.getAll().observe(viewLifecycleOwner) { dataList ->
                Log.e("getAll", "Observe")
                todayData.clear()
                for (data in dataList){
                    when(it){
                        1 -> if(data.day?.get(0) == true) todayData.add(data)
                        2 -> if(data.day?.get(1) == true) todayData.add(data)
                        3 -> if(data.day?.get(2) == true) todayData.add(data)
                        4 -> if(data.day?.get(3) == true) todayData.add(data)
                        5 -> if(data.day?.get(4) == true) todayData.add(data)
                        6 -> if(data.day?.get(5) == true) todayData.add(data)
                        7 -> if(data.day?.get(6) == true) todayData.add(data)
                    }
                }
                adapter.setData(todayData)
            }
        }
    }
}