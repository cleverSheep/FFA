package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.util.TestData

class WorkTasksFragment : Fragment() {
    private lateinit var workTasks: RecyclerView
    private lateinit var fabEditTask: FloatingActionButton
    private lateinit var fabRefreshTasks: FloatingActionButton
    private lateinit var fabAddTask: FloatingActionButton
    private val alertDialog = AddTaskDialog()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workTasks = view.findViewById(R.id.rv_work_tasks)

        val adapter = WorkTaskAdapter(TestData.WORKTASKS)
        workTasks.adapter = adapter
        workTasks.layoutManager = LinearLayoutManager(activity)

        fabAddTask = view.findViewById(R.id.fab_add_task)
        fabEditTask = view.findViewById(R.id.fab_edit_task)
        fabRefreshTasks = view.findViewById(R.id.fab_refresh_tasks)

        fabAddTask.setOnClickListener {
            alertDialog.showDialog(activity)
            alertDialog._addedTask.observe(viewLifecycleOwner) {
                if (it != null) {
                    adapter.addTask(it)
                } else {
                    Log.d("WorkTasksFragment", "Work task is null")
                }
            }
        }

        fabEditTask.setOnClickListener {}

        fabRefreshTasks.setOnClickListener {}

    }
}