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
import com.product.eamfieldaccess.models.WorkTask
import com.product.eamfieldaccess.util.TestData.Companion.AUTHENTICATED_EMPLOYEE
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
        model.currentWorkOrder.observe(viewLifecycleOwner) { workOrder ->
            val adapter =
                WorkTaskAdapter(
                    workOrder.workTasks,
                    this::onTaskTimeUpdated,
                    workOrder,
                    this::onEmployeeAdded
                )
            binding.rvWorkTasks.adapter = adapter
            binding.rvWorkTasks.layoutManager = LinearLayoutManager(activity)
            if (workOrder.employeeId != AUTHENTICATED_EMPLOYEE.id) {
                binding.fabAddTask.visibility = View.GONE
            }
            binding.fabAddTask.setOnClickListener {
                alertDialog.showDialog(activity, this::onTaskAdded, workOrder.id)
            }
        }
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

    fun onEmployeeAdded(labor: Labor) {
        model.currentWorkOrder.observeOnce(viewLifecycleOwner) { workOrder ->
            workOrder.labor.add(labor)
            model.currentWorkOrder.postValue(workOrder)
        }
    }

    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }

}