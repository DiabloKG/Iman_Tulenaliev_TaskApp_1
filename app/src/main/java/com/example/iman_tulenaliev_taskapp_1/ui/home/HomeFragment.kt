package com.example.iman_tulenaliev_taskapp_1.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iman_tulenaliev_taskapp_1.App
import com.example.iman_tulenaliev_taskapp_1.R
import com.example.iman_tulenaliev_taskapp_1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.fabHome.setOnClickListener {
            findNavController().navigate(R.id.navigation_newTask)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter(this::onLongClickListener)
    }

    private fun initViews() {
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = taskAdapter
        getDataFromLocalDB()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_sort) {
            val items = arrayOf("Date", "A-Z", "Z-A")
            val builder = AlertDialog.Builder(requireContext())
            with(builder)
            {
                setTitle("Sort By")
                setItems(items) { dialog, which ->
                    when (which) {
                        0 -> {
                            taskAdapter.addTasksFromRoom(App.db.dao().getAllTaskByDate())
                            dialog.dismiss()
                        }
                        1 -> {
                            taskAdapter.addTasksFromRoom(App.db.dao().getAllTaskByAlphabetAZ())
                            dialog.dismiss()
                        }
                        2 -> {
                            taskAdapter.addTasksFromRoom(App.db.dao().getAllTaskByAlphabetZA())
                            dialog.dismiss()
                        }
                    }
                }
                show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getDataFromLocalDB() {
        val listOfTask = App.db.dao().getAllTask()
        taskAdapter.addTasksFromRoom(listOfTask)
    }

    private fun onLongClickListener(position: Int) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Deleting")
        builder.setMessage("Are you sure to delete? " + taskAdapter.getTask(position).title)
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            App.db.dao().deleteTask(taskAdapter.getTask(position))
            getDataFromLocalDB()
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}