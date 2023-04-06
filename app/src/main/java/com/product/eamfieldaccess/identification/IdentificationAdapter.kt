package com.product.eamfieldaccess.identification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.*
import com.product.eamfieldaccess.workselection.WorkSelectionItem
import java.io.Serializable

class IdentificationAdapter : RecyclerView.Adapter<IdentificationAdapter.ViewHolder>() {
    private var employees: ArrayList<EmployeeExtension> = ArrayList()

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
        val employee = employees[position]
        holder.employeeId.setContent(employee.uuid)
        holder.employeeName.setContent(employee.employeeName)

        val workOrders = employee.workOrders
        val bundle = Bundle()
        val serializedWorkOrders = WorkOrders(workOrders!!.toList())
        bundle.putSerializable("work_orders", serializedWorkOrders)
        holder.itemView.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_identification_to_workSelection, bundle)
        }
    }

    override fun getItemCount() = employees.size

    fun addEmployees(employees: List<EmployeeExtension>) {
        this.employees.addAll(employees)
        notifyDataSetChanged()
    }

    fun addEmployee(employee: EmployeeExtension) {
        this.employees.add(employee)
        notifyDataSetChanged()
    }

    data class WorkOrders(
        val orders: List<WorkOrderExtension>
    ) : Serializable
}