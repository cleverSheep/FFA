package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.product.eamfieldaccess.databinding.FragmentWorkTasksBinding
import com.product.eamfieldaccess.models.TaskTime
import com.product.eamfieldaccess.models.WorkTask
import com.product.eamfieldaccess.workselection.WorkOrderViewModel

class WorkTasksFragment : Fragment() {
    private val alertDialog = AddTaskDialog()

    private lateinit var model: WorkOrderViewModel
    private lateinit var binding: FragmentWorkTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        model = ViewModelProvider(requireActivity())[WorkOrderViewModel::class.java]
        binding = FragmentWorkTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.currentWorkOrder.observe(viewLifecycleOwner) {
            val adapter =
                WorkTaskAdapter(it.workTasks as ArrayList<WorkTask>, this::onTaskTimeUpdated)
            binding.rvWorkTasks.adapter = adapter
            binding.rvWorkTasks.layoutManager = LinearLayoutManager(activity)

            binding.fabAddTask.setOnClickListener {
                alertDialog.showDialog(activity)
                alertDialog._addedTask.observe(viewLifecycleOwner) { task ->
                    if (task != null) {
                        adapter.addTask(task)
                    } else {
                        Log.d("WorkTasksFragment", "Work task is null")
                    }
                }
            }
        }

        // binding.fabRefreshTasks.setOnClickListener {}
    }

    fun onTaskTimeUpdated(
        taskTime: TaskTime
    ) {
        model.currentTime.postValue(taskTime)
    }
}