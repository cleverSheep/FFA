package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.WorkTask
import com.product.eamfieldaccess.util.TestData


class AddTaskDialog {

    private lateinit var cancel: Button
    private lateinit var submit: Button

    private lateinit var code: Spinner

    var _addedTask = MutableLiveData<WorkTask>()

    fun showDialog(activity: Activity?) {
        if (activity != null) {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.add_task_dialog)

            cancel = dialog.findViewById(R.id.add_task_cancel)
            submit = dialog.findViewById(R.id.add_task_submit)

            code = dialog.findViewById(R.id.add_task_code)

            val allTasks = TestData.ALL_WORK_WORK_TASKS
            val codes = allTasks.map {
                it.code
            }
            val items = codes.toTypedArray()
            val adapter = ArrayAdapter(
                activity,
                android.R.layout.simple_spinner_dropdown_item,
                items
            )
            code.adapter = adapter

            val mappedTasks = mutableMapOf<String, WorkTask>()
            allTasks.forEach {
                mappedTasks[it.code] = it
            }

            cancel.setOnClickListener {
                dialog.dismiss()
            }
            submit.setOnClickListener {
                val task = mappedTasks[code.selectedItem.toString()]!!
                _addedTask.value = task
                dialog.dismiss()
            }
            dialog.show()
        } else {
            Log.d("AddTaskDialog", "The activity is null!")
        }

    }


}