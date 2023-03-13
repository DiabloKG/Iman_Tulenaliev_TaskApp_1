package com.example.iman_tulenaliev_taskapp_1.ui.home.new_Task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.iman_tulenaliev_taskapp_1.App
import com.example.iman_tulenaliev_taskapp_1.R
import com.example.iman_tulenaliev_taskapp_1.database.local.TaskDatabase
import com.example.iman_tulenaliev_taskapp_1.databinding.FragmentNewTaskBinding
import com.example.iman_tulenaliev_taskapp_1.ui.home.HomeFragment.Companion.EDIT_KEY
import com.example.iman_tulenaliev_taskapp_1.ui.home.TaskModel

class NewTaskFragment : Fragment() {

    private lateinit var task: TaskModel
    private lateinit var binding: FragmentNewTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            if (arguments != null) {
                updateTaskRoom(task)
            } else {
                saveTaskRoom()
            }
        }
    }

    private fun saveTaskRoom(){
        App.db.dao().insert(
            TaskModel(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
        )
        Log.e("kot11", "Room inserted successfully")
        findNavController().navigateUp()
    }

    private fun updateTaskRoom(task: TaskModel){
        task.title = binding.etTitle.text.toString()
        task.desc = binding.etDesc.text.toString()
        App.db.dao().update(task)
        findNavController().navigateUp()
    }

    private fun initViews() {
        if (arguments != null){
            task = arguments?.getSerializable(EDIT_KEY) as TaskModel
            binding.etTitle.setText(task.title)
            binding.etDesc.setText(task.desc)
            binding.btnSave.text = "Update"
        } else {
            binding.btnSave.text = "Save"
        }
    }

    companion object {
        const val TASK_KEY = "new_task"
    }
}