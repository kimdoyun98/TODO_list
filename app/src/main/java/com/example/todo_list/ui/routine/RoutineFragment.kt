package com.example.todo_list.ui.routine

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todo_list.R
import com.example.todo_list.adapter.routine.RoutineAdapter
import com.example.todo_list.alarm.Alarm
import com.example.todo_list.data.room.RoutineEntity
import com.example.todo_list.databinding.FragmentRoutineBinding
import com.example.todo_list.ui.routine.add.RoutineRegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoutineFragment : Fragment() {
    private var _binding: FragmentRoutineBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoutineViewModel by viewModels()
    @Inject
    lateinit var alarm: Alarm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RoutineAdapter { routineEntity -> showDeleteDialog(routineEntity) }
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter

        viewModel.getAll.observe(viewLifecycleOwner) { data ->
            adapter.submitList(data)
        }

        /***
         * 추가 버튼
         */
        binding.addButton.setOnClickListener {
            val intent = Intent(context, RoutineRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDeleteDialog(routineEntity: RoutineEntity) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(context.getString(R.string.routine_delete_dialog_title))
            setMessage(routineEntity.title)
            setPositiveButton(context.getString(R.string.dialog_positive_text)) { _, _ ->
                viewModel.delete(routineEntity.id)
                alarm.cancelAlarm(routineEntity.id)
            }
            setNegativeButton(context.getString(R.string.dialog_negative_text)) { _, _ ->

            }
            show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoutineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
