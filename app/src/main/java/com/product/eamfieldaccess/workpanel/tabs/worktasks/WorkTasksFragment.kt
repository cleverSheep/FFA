package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var workTaskAdapter: WorkTaskAdapter

    private val columns = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

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
                    this::onEmployeeAdded,
                    this::onImagesAdded,
                    model
                )
            workTaskAdapter = adapter
            binding.rvWorkTasks.adapter = adapter
            binding.rvWorkTasks.layoutManager = LinearLayoutManager(activity)
            if (workOrder.employeeId != AUTHENTICATED_EMPLOYEE.id) {
                binding.fabAddTask.visibility = View.GONE
            }
            binding.fabAddTask.setOnClickListener {
                alertDialog.showDialog(activity, this::onTaskAdded, workOrder.id)
            }
        }
        if ((ActivityCompat.checkSelfPermission(
                requireContext(), columns[0]
            ) != PackageManager.PERMISSION_GRANTED) &&
            (ActivityCompat.checkSelfPermission(
                requireContext(), columns[1]
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(columns, 123);
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

    fun onImagesAdded() {
        openGallery()
    }

    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && resultCode == RESULT_OK) {
            val list = arrayListOf<Uri>()
            if (list.size > 3) {
                Toast.makeText(
                    requireContext(),
                    "A maximum of three images are allowed",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            if (data!!.clipData != null) {
                val x = data.clipData!!.itemCount
                for (i in 0 until x) {
                    list.add(data.clipData!!.getItemAt(i).uri)
                }
                model.currentWorkTask.observeOnce(viewLifecycleOwner) { workTask ->
                    workTaskAdapter.addImages(list, workTask)
                }
            } else if (data.data != null) {
                val imgurl = data.data!!.path
                list.add(Uri.parse(imgurl))
                model.currentWorkTask.observeOnce(viewLifecycleOwner) { workTask ->
                    workTaskAdapter.addImages(list, workTask)
                }
            }
        }
    }

}