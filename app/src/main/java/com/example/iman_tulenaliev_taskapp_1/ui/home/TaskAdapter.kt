package com.example.iman_tulenaliev_taskapp_1.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iman_tulenaliev_taskapp_1.databinding.TaskItemBinding

class TaskAdapter(val onLongClick: (Int) -> Unit,
                  val onlick: (Int) -> Unit) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private var taskList = arrayListOf<TaskModel>()

    fun addTasksFromRoom(list: List<TaskModel>) {
        taskList = list as ArrayList<TaskModel>
        notifyDataSetChanged()
    }

    fun getTask(pos: Int): TaskModel {
        return taskList[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(taskModel: TaskModel) {
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.desc
            itemView.setOnLongClickListener {
                onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
            itemView.setOnClickListener{
                onlick(adapterPosition)
            }
        }
    }
}