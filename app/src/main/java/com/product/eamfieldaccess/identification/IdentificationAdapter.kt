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
    private var employees: ArrayList<Employee> = ArrayList()

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
        holder.employeeId.setContent(data.uuid)
        holder.employeeName.setContent(data.employeeName)

        val workOrders = employees[position].workOrders
        val bundle = Bundle()
        val serializedWorkOrders = WorkOrders(mapWorkOrder(workOrders))
        bundle.putSerializable("work_orders", serializedWorkOrders)
        holder.itemView.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_identification_to_workSelection, bundle)
        }
    }

    override fun getItemCount() = employees.size

    fun addEmployees(employees: List<Employee>) {
        this.employees.addAll(employees)
        notifyDataSetChanged()
    }

    fun addEmployee(employee: Employee) {
        this.employees.add(employee)
        notifyDataSetChanged()
    }

    private fun mapWorkOrder(workOrders: List<WorkOrder>): List<WorkOrderExtension> {
        val workOrderExtensions = mutableListOf<WorkOrderExtension>()
        val allWorkTasks = ArrayList<WorkTaskExtension>()
        val allWorkLabor = ArrayList<WorkLaborExtension>()

        workOrders.forEach { workOrder ->
            workOrder.workTasks?.let {
                it.forEach { workTask ->
                    val workTask = WorkTaskExtension(
                        workOrderId = workTask.workOrderId,
                        category = workTask.category,
                        employeeId = workTask.employeeId,
                        code = workTask.code,
                        description = workTask.description ?: "---",
                        notes = workTask.notes ?: "---",
                        startTime = "---",
                        endTime = "---",
                        totalTime = "---",
                        attachmentLabel = "---"
                    )
                    allWorkTasks.add(workTask)
                }
            }
            workOrder.workLabor?.let {
                it.forEach { labor ->
                    val workLabor = WorkLaborExtension(
                        workOrderId = labor.workOrderId,
                        employeeId = labor.employeeId,
                        workTaskCode = labor.workTaskCode,
                        employeeName = labor.employeeName,
                        date = labor.dateOpened,
                        startTime = labor.startTime,
                        endTime = labor.endTime,
                        totalTime = labor.totalTime,
                        system = labor.system,
                        description = labor.description
                    )
                    allWorkLabor.add(workLabor)
                }
            }
            val workOrderExtension = WorkOrderExtension(
                id = workOrder.id,
                site = workOrder.site,
                employeeUUID = workOrder.employeeUUID,
                location = workOrder.location,
                unit = workOrder.unit,
                workOrder = workOrder.workOrder,
                status = workOrder.status,
                activity = workOrder.activity ?: "---",
                maintenance = "---",
                dateOpened = workOrder.dateOpened,
                requester = workOrder.requestor ?: "---",
                notes = "---",
                standardTask = workOrder.standardTask ?: "---",
                workTasks = allWorkTasks,
                workLabor = allWorkLabor,
                startTime = "",
                endTime = "",
                checkLists = workOrder.checkLists ?: emptyList()
            )
            workOrderExtensions.add(workOrderExtension)
        }
        return workOrderExtensions
    }

    data class WorkOrders(
        val orders: List<WorkOrderExtension>
    ) : Serializable
}