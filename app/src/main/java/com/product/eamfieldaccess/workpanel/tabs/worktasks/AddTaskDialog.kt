package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.WorkTask


class AddTaskDialog {

    private lateinit var cancel: Button
    private lateinit var submit: Button

    private lateinit var category: EditText
    private lateinit var code: EditText
    private lateinit var description: EditText
    private lateinit var notes: EditText

    var _addedTask = MutableLiveData<WorkTask>()

    fun showDialog(activity: Activity?) {
        if (activity != null) {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.add_task_dialog)

            cancel = dialog.findViewById(R.id.add_task_cancel)
            submit = dialog.findViewById(R.id.add_task_submit)

            category = dialog.findViewById(R.id.add_task_category)
            code = dialog.findViewById(R.id.add_task_code)
            description = dialog.findViewById(R.id.add_task_description)
            notes = dialog.findViewById(R.id.add_task_notes)

            cancel.setOnClickListener {
                dialog.dismiss()
            }
/*            submit.setOnClickListener {
                val task = WorkTask(
                    category.text.toString(),
                    code.text.toString(),
                    description.text.toString(),
                    notes.text.toString()
                )
                _addedTask.value = task
                dialog.dismiss()
            }*/
            dialog.show()
        } else {
            Log.d("AddTaskDialog", "The activity is null!")
        }

    }


}