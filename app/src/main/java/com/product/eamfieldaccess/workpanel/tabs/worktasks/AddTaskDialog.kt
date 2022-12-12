package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.Labor
import com.product.eamfieldaccess.models.WorkTask
import com.product.eamfieldaccess.util.TestData
import com.product.eamfieldaccess.util.TestData.Companion.AUTHENTICATED_EMPLOYEE
import java.util.*


class AddTaskDialog {

    private lateinit var cancel: Button
    private lateinit var submit: Button

    private lateinit var code: Spinner

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

            cancel.setOnClickListener {
                dialog.dismiss()
            }
            submit.setOnClickListener {
                val task = mappedTasks[code.selectedItem.toString()]!!
                task.workOrderId = workOrderId
                task.employeeId = AUTHENTICATED_EMPLOYEE.id
                val employee = AUTHENTICATED_EMPLOYEE
                val labor = Labor(
                    workOrderId,
                    employee.id,
                    task.code,
                    employee.name,
                    false,
                    0, 0,
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