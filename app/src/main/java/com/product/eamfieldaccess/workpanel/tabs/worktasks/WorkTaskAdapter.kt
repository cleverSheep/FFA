package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.TaskTime
import com.product.eamfieldaccess.models.WorkTask
import com.product.eamfieldaccess.util.Utils
import com.product.eamfieldaccess.workselection.WorkSelectionItem
import java.util.*
import kotlin.collections.ArrayList

class WorkTaskAdapter(
    private val dataSet: ArrayList<WorkTask> = arrayListOf(),
    private val itemClickListener: ((
        taskTime: TaskTime
    ) -> Unit)? = null
) : RecyclerView.Adapter<WorkTaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val workCategory = view.findViewById<WorkSelectionItem>(R.id.work_task_category)
        val workCode = view.findViewById<WorkSelectionItem>(R.id.work_task_code)
        val workDescription = view.findViewById<WorkSelectionItem>(R.id.work_task_description)
        val workNotes = view.findViewById<WorkSelectionItem>(R.id.work_task_notes)
        val startTask = view.findViewById<Button>(R.id.button_start)
        val endTask = view.findViewById<Button>(R.id.button_end)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.work_task_item, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.workCategory.setContent(dataSet[position].category)
        holder.workCode.setContent(dataSet[position].code)
        holder.workDescription.setContent(dataSet[position].description)
        holder.workNotes.setContentForEditableText(dataSet[position].notes)
        holder.endTask.isEnabled = false

        val workOrderId = dataSet[position].workOrderId
        val workTaskCode = dataSet[position].code

        if (Utils.TASK_RUNNING["${workOrderId}-${workTaskCode}"] == true) {
            holder.endTask.isEnabled = true
            holder.startTask.isEnabled = false
        }

        holder.startTask.setOnClickListener {
            Utils.TASK_RUNNING["${workOrderId}-${workTaskCode}"] = true
            holder.endTask.isEnabled = true
            it.isEnabled = false

            val startTime = Calendar.getInstance().time
            Utils.TASK_START_TIME["${workOrderId}-${workTaskCode}"] = startTime

            val endTime = "---"
            val totalTime = "---"
            itemClickListener?.let { invoke ->
                invoke(
                    TaskTime(
                        workOrderId,
                        workTaskCode,
                        startTime.toString(),
                        endTime,
                        totalTime
                    )
                )
            }
        }

        holder.endTask.setOnClickListener {
            Utils.TASK_RUNNING["${workOrderId}-${workTaskCode}"] = false
            holder.startTask.isEnabled = true
            it.isEnabled = false

            val endTime = Calendar.getInstance().time
            val startTime = Utils.TASK_START_TIME["${workOrderId}-${workTaskCode}"]
            val totalTime = endTime.time - startTime!!.time

            itemClickListener?.let { invoke ->
                invoke(
                    TaskTime(
                        workOrderId,
                        workTaskCode,
                        startTime.toString(),
                        endTime.toString(),
                        "${totalTime.toInt() / 1000 / 60 / 60} hours"
                    )
                )
            }
        }
    }

    override fun getItemCount() = dataSet.size

    fun addTask(task: WorkTask) {
        dataSet.add(task)
        notifyItemInserted(dataSet.size - 1);
    }
}