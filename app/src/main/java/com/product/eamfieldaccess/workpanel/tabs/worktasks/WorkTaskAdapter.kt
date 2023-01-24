package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.WorkLaborExtension
import com.product.eamfieldaccess.models.TaskTime
import com.product.eamfieldaccess.models.WorkOrderExtension
import com.product.eamfieldaccess.models.WorkTaskExtension
import com.product.eamfieldaccess.util.PausedTime
import com.product.eamfieldaccess.util.Utils
import com.product.eamfieldaccess.workselection.WorkOrderViewModel
import com.product.eamfieldaccess.workselection.WorkSelectionItem
import java.util.*


class WorkTaskAdapter(
    private val dataSet: List<WorkTaskExtension>,
    private val itemClickListener: ((
        taskTime: TaskTime
    ) -> Unit)? = null,
    private val workOrderExtension: WorkOrderExtension,
    private val onEmployeeAdded: ((
        workLaborExtension: WorkLaborExtension
    ) -> Unit),
    private val onImagesAdded: () -> Unit,
    private val workOrderViewModel: WorkOrderViewModel
) : RecyclerView.Adapter<WorkTaskAdapter.ViewHolder>() {

    fun addImages(images: ArrayList<Uri>, position: Int) {
        images.forEachIndexed { index, _ ->
            if (index == 0) {
                dataSet[position].attachmentLabel = "Attachment Added"
            } else {
                dataSet[position].attachmentLabel += "\nAttachment Added"
            }
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val workCategory = view.findViewById<WorkSelectionItem>(R.id.work_task_category)
        val workCode = view.findViewById<WorkSelectionItem>(R.id.work_task_code)
        val workDescription = view.findViewById<WorkSelectionItem>(R.id.work_task_description)
        val workNotes = view.findViewById<WorkSelectionItem>(R.id.work_task_notes)
        val startTask = view.findViewById<Button>(R.id.button_start)
        val pauseTask = view.findViewById<Button>(R.id.button_stop)
        val endTask = view.findViewById<Button>(R.id.button_end)
        val signOnTask = view.findViewById<Button>(R.id.sign_on_button)
        val attachmentLabel = view.findViewById<TextView>(R.id.attachment_label)
        val addImages = view.findViewById<Button>(R.id.add_images_button)
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
        holder.signOnTask.visibility = View.GONE
        holder.addImages.setOnClickListener {
            onImagesAdded()
            workOrderViewModel.currentWorkTask.postValue(position)
        }
        holder.attachmentLabel.text = dataSet[position].attachmentLabel

        var employeeId = ""

        val taskCode =
            "${dataSet[position].workOrderId}-${dataSet[position].code}-${Utils.AUTH_EMPLOYEE!!.uuid}"
        holder.signOnTask.setOnClickListener {
            signEmployeeToTask(position)
            Utils.CURRENT_EMPLOYEE_ADDED_TASK.add(taskCode)
        }
        if (workOrderExtension.employeeUUID != Utils.AUTH_EMPLOYEE!!.uuid && !Utils.CURRENT_EMPLOYEE_ADDED_TASK.contains(
                taskCode
            )
        ) {
            holder.signOnTask.visibility = View.VISIBLE
            holder.startTask.isEnabled = false
            holder.endTask.isEnabled = false
            employeeId = Utils.AUTH_EMPLOYEE!!.uuid
        } else if (workOrderExtension.employeeUUID != Utils.AUTH_EMPLOYEE!!.uuid) {
            employeeId = Utils.AUTH_EMPLOYEE!!.uuid
        } else {
            employeeId = dataSet[position].employeeId
            holder.signOnTask.visibility = View.GONE
        }
        trackWorkTime(holder, position, employeeId)

    }

    override fun getItemCount() = dataSet.size

    fun signEmployeeToTask(position: Int) {
        // create a new labor entry w/ the authenticated employee info
        val workLaborExtension = WorkLaborExtension(
            workOrderExtension.id,
            Utils.AUTH_EMPLOYEE!!.uuid,
            dataSet[position].code,
            Utils.AUTH_EMPLOYEE!!.employeeName,
            false,
            0,
            0,
            Calendar.getInstance().time.toString(),
            "---",
            "---",
            "---",
            dataSet[position].category,
            dataSet[position].description
        )
        onEmployeeAdded(workLaborExtension)
    }

    fun trackWorkTime(holder: ViewHolder, position: Int, employeeId: String) {
        holder.endTask.isEnabled = false
        holder.pauseTask.isEnabled = false

        val workOrderId = dataSet[position].workOrderId
        val workTaskCode = dataSet[position].code

        if (Utils.TASK_RUNNING["${workOrderId}-${workTaskCode}-${employeeId}"] == true) {
            holder.endTask.isEnabled = true
            holder.pauseTask.isEnabled = true
            holder.startTask.isEnabled = false
        } else {
            val pausedTime = Utils.TASK_PAUSED["${workOrderId}-${workTaskCode}-${employeeId}"]
            pausedTime?.let {
                if (it.isOnBreak) {

                    holder.pauseTask.isEnabled = false
                    holder.endTask.isEnabled = true
                    holder.startTask.isEnabled = true
                }
            }
        }

        holder.startTask.setOnClickListener {
            // resume the task, so we shouldn't define a start time
            if (Utils.TASK_PAUSED["${workOrderId}-${workTaskCode}-${employeeId}"] != null) {
                val pausedTime = Utils.TASK_PAUSED["${workOrderId}-${workTaskCode}-${employeeId}"]!!
                if (pausedTime.isOnBreak) {
                    pausedTime.isOnBreak = false
                    pausedTime.totalBreakTime +=
                        Calendar.getInstance().time.time - pausedTime.timeBreakTaken.time
                    pausedTime.timeTaskResumed = Calendar.getInstance().time
                }
            } else { // start the task, so we should define a start time
                val startTime = Calendar.getInstance().time
                Utils.TASK_START_TIME["${workOrderId}-${workTaskCode}-${employeeId}"] = startTime
            }
            Utils.TASK_RUNNING["${workOrderId}-${workTaskCode}-${employeeId}"] = true
            holder.endTask.isEnabled = true
            holder.pauseTask.isEnabled = true
            it.isEnabled = false

            val endTime = "---"
            val totalTime = "---"
            itemClickListener?.let { invoke ->
                invoke(
                    TaskTime(
                        workOrderId,
                        workTaskCode,
                        employeeId,
                        Utils.TASK_START_TIME["${workOrderId}-${workTaskCode}-${employeeId}"].toString(),
                        endTime,
                        totalTime
                    )
                )
            }
        }

        holder.pauseTask.setOnClickListener { button ->
            // The first time the task is paused
            if (Utils.TASK_PAUSED["${workOrderId}-${workTaskCode}-${employeeId}"] == null) {
                val pausedTime = PausedTime(
                    isOnBreak = true,
                    totalBreakTime = 0,
                    timeBreakTaken = Calendar.getInstance().time,
                    null

                )
                Utils.TASK_PAUSED["${workOrderId}-${workTaskCode}-${employeeId}"] = pausedTime
            } else {
                val pausedTime = Utils.TASK_PAUSED["${workOrderId}-${workTaskCode}-${employeeId}"]!!
                pausedTime.isOnBreak = true
                pausedTime.timeBreakTaken = Calendar.getInstance().time
            }
            Utils.TASK_RUNNING["${workOrderId}-${workTaskCode}-${employeeId}"] = false
            button.isEnabled = false
            holder.startTask.isEnabled = true
            holder.endTask.isEnabled = true
        }

        // TODO: clear the pause time after a task is finished
        holder.endTask.setOnClickListener {
            var breakTime = 0L
            if (Utils.TASK_PAUSED["${workOrderId}-${workTaskCode}-${employeeId}"] != null) {
                val pausedTime = Utils.TASK_PAUSED["${workOrderId}-${workTaskCode}-${employeeId}"]!!
                if (pausedTime.isOnBreak) {
                    pausedTime.isOnBreak = false
                    pausedTime.totalBreakTime +=
                        Calendar.getInstance().time.time - pausedTime.timeBreakTaken.time
                }
                breakTime = pausedTime.totalBreakTime
                Utils.TASK_PAUSED["${workOrderId}-${workTaskCode}-${employeeId}"] = null
            }
            Utils.TASK_RUNNING["${workOrderId}-${workTaskCode}-${employeeId}"] = false
            holder.startTask.isEnabled = true
            holder.pauseTask.isEnabled = false
            it.isEnabled = false

            val endTime = Calendar.getInstance().time
            val startTime = Utils.TASK_START_TIME["${workOrderId}-${workTaskCode}-${employeeId}"]
            val totalTime = endTime.time - startTime!!.time - breakTime

            itemClickListener?.let { invoke ->
                invoke(
                    TaskTime(
                        workOrderId,
                        workTaskCode,
                        employeeId,
                        startTime.toString(),
                        endTime.toString(),
                        "${totalTime.toInt() / 1000 / 60 / 60} hours"
                    )
                )
            }
        }
    }
}