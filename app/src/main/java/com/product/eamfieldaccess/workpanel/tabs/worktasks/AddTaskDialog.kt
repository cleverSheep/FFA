package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.Employee
import com.product.eamfieldaccess.models.Labor
import com.product.eamfieldaccess.models.WorkTask
import com.product.eamfieldaccess.util.TestData
import java.util.*


class AddTaskDialog {

    private lateinit var cancel: Button
    private lateinit var submit: Button

    private lateinit var code: Spinner
    private lateinit var employees: Spinner

    fun showDialog(
        activity: Activity?,
        addTask: (workTask: WorkTask, labor: Labor) -> Unit,
        workOrderId: String
    ) {

        if (activity != null) {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.add_task_dialog)

            cancel = dialog.findViewById(R.id.add_task_cancel)
            submit = dialog.findViewById(R.id.add_task_submit)

            code = dialog.findViewById(R.id.add_task_code)
            employees = dialog.findViewById(R.id.add_employee_id)

            val sampleTasks = TestData.ALL_WORK_WORK_TASKS
            val workCodes = sampleTasks.map {
                it.code
            }
            val workCodesArray = workCodes.toTypedArray()
            val adapter = ArrayAdapter(
                activity,
                android.R.layout.simple_spinner_dropdown_item,
                workCodesArray
            )
            code.adapter = adapter

            val mappedTasks = mutableMapOf<String, WorkTask>()
            sampleTasks.forEach {
                mappedTasks[it.code] = it
            }

            val sampleEmployees = TestData.EMPLOYEES
            val employeeIds = sampleEmployees.map {
                it.id
            }

            val employeesArray = employeeIds.toTypedArray()
            val employeesAdapter = ArrayAdapter(
                activity,
                android.R.layout.simple_spinner_dropdown_item,
                employeesArray
            )
            employees.adapter = employeesAdapter

            val mappedEmployees = mutableMapOf<String, Employee>()
            sampleEmployees.forEach {
                mappedEmployees[it.id] = it
            }

            cancel.setOnClickListener {
                dialog.dismiss()
            }
            submit.setOnClickListener {
                val task = mappedTasks[code.selectedItem.toString()]!!
                task.workOrderId = workOrderId
                val employee = mappedEmployees[employees.selectedItem.toString()]!!
                val labor = Labor(
                    workOrderId,
                    employee.id,
                    task.code,
                    employee.name,
                    Calendar.getInstance().time.toString(),
                    "---",
                    "---",
                    "---",
                    task.category,
                    task.description
                )
                addTask(task, labor)
                dialog.dismiss()
            }
            dialog.show()
        } else {
            Log.d("AddTaskDialog", "The activity is null!")
        }

    }

}