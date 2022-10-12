package com.product.eamfieldaccess.workpanel.tabs.worktasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.WorkTask
import com.product.eamfieldaccess.workselection.WorkSelectionItem

class WorkTaskAdapter(
    private val dataSet: ArrayList<WorkTask>
) : RecyclerView.Adapter<WorkTaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val workCategory = view.findViewById<WorkSelectionItem>(R.id.work_task_category)
        val workCode = view.findViewById<WorkSelectionItem>(R.id.work_task_code)
        val workDescription = view.findViewById<WorkSelectionItem>(R.id.work_task_description)
        val workNotes = view.findViewById<WorkSelectionItem>(R.id.work_task_notes)
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
    }

    override fun getItemCount() = dataSet.size

    fun addTask(task: WorkTask) {
        dataSet.add(task)
        notifyItemInserted(dataSet.size - 1);
    }
}