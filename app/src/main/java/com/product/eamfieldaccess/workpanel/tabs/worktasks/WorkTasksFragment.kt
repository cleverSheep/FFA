package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.product.eamfieldaccess.databinding.FragmentWorkTasksBinding
import com.product.eamfieldaccess.models.Labor
import com.product.eamfieldaccess.models.TaskTime
import com.product.eamfieldaccess.models.WorkOrder
import com.product.eamfieldaccess.models.WorkTask
import com.product.eamfieldaccess.workselection.WorkOrderViewModel
import java.util.*

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
        model.currentWorkOrder.observe(viewLifecycleOwner) { workOrder ->
            val adapter =
                WorkTaskAdapter(workOrder.workTasks, this::onTaskTimeUpdated)
            binding.rvWorkTasks.adapter = adapter
            binding.rvWorkTasks.layoutManager = LinearLayoutManager(activity)

            binding.fabAddTask.setOnClickListener {
                alertDialog.showDialog(activity, this::onTaskAdded, workOrder.id)
            }
        }

        // binding.fabRefreshTasks.setOnClickListener {}
    }

    fun onTaskTimeUpdated(
        taskTime: TaskTime
    ) {
        model.currentTime.postValue(taskTime)
    }

    fun onTaskAdded(workTask: WorkTask, labor: Labor) {
        model.currentWorkOrder.observeOnce(viewLifecycleOwner) { workOrder ->
            workOrder.workTasks.add(workTask)
            workOrder.labor.add(labor)
            model.currentWorkOrder.postValue(workOrder)
        }
    }

    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }

}