package com.product.eamfieldaccess.workpanel.tabs.worklabor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.Labor
import com.product.eamfieldaccess.workselection.WorkSelectionItem

class WorkLaborAdapter(
    private val dataSet: ArrayList<Labor>
) : RecyclerView.Adapter<WorkLaborAdapter.ViewHolder>() {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val id = view.findViewById<WorkSelectionItem>(R.id.work_labor_id)
        val employeeName = view.findViewById<WorkSelectionItem>(R.id.work_labor_employee_name)
        val date = view.findViewById<WorkSelectionItem>(R.id.work_labor_date)
        val startTime = view.findViewById<WorkSelectionItem>(R.id.work_labor_start)
        val stopTime = view.findViewById<WorkSelectionItem>(R.id.work_labor_stop)
        val time = view.findViewById<WorkSelectionItem>(R.id.work_labor_time)
        val system = view.findViewById<WorkSelectionItem>(R.id.work_labor_system)
        val taskDescription = view.findViewById<WorkSelectionItem>(R.id.work_labor_task_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.work_labor_item, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.employeeName.setContent(dataSet[position].employeeName)
        holder.id.setContent(dataSet[position].id)
        holder.date.setContent(dataSet[position].date)
        holder.startTime.setContent(dataSet[position].startTime)
        holder.stopTime.setContent(dataSet[position].endTime)
        holder.time.setContent(dataSet[position].totalTime.toString())
        holder.system.setContent(dataSet[position].system)
        holder.taskDescription.setContentForEditableText(dataSet[position].description)
    }

    override fun getItemCount() = dataSet.size
}