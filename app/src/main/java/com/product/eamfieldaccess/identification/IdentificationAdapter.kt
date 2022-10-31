package com.product.eamfieldaccess.identification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.Employee
import com.product.eamfieldaccess.models.WorkOrder
import com.product.eamfieldaccess.workselection.WorkSelectionAdapter
import com.product.eamfieldaccess.workselection.WorkSelectionItem
import java.io.Serializable

class IdentificationAdapter(private val employees: List<Employee>) :
    RecyclerView.Adapter<IdentificationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val employeeId: WorkSelectionItem
        val employeeName: WorkSelectionItem

        init {
            employeeId = view.findViewById(R.id.employee_id)
            employeeName = view.findViewById(R.id.employee_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.identification_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = employees[position]
        holder.employeeId.setContent(data.id)
        holder.employeeName.setContent(data.name)

        val workOrders = employees[position].sites.map { it.workOrders }.flatten()
        val bundle = Bundle()
        val serializedWorkOrders = WorkOrders(workOrders)
        bundle.putSerializable("work_orders", serializedWorkOrders)
        holder.itemView.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_identification_to_workSelection, bundle)
        }
    }

    override fun getItemCount() = employees.size

    data class WorkOrders(
        val orders: List<WorkOrder>
    ) : Serializable
}