package com.product.eamfieldaccess.workselection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.WorkOrder

class WorkSelectionAdapter(private val workOrders: List<WorkOrder>) :
    RecyclerView.Adapter<WorkSelectionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val workOrderId: TextView
        val workOrderSite: WorkSelectionItem
        val workOrderUnit: WorkSelectionItem
        val workOrderStatus: WorkSelectionItem
        val workOrderActivity: WorkSelectionItem
        val workOrderMaintLevel: WorkSelectionItem
        val workOrderNotes: WorkSelectionItem

        init {
            workOrderId = view.findViewById(R.id.work_order_id)
            workOrderSite = view.findViewById(R.id.work_order_site)
            workOrderUnit = view.findViewById(R.id.work_order_unit)
            workOrderStatus = view.findViewById(R.id.work_order_status)
            workOrderActivity = view.findViewById(R.id.work_order_activity_reason)
            workOrderMaintLevel = view.findViewById(R.id.work_order_maint_level)
            workOrderNotes = view.findViewById(R.id.work_order_notes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.work_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = workOrders[position]
        holder.workOrderId.text = data.workOrder
        holder.workOrderSite.setContent(data.site)
        holder.workOrderUnit.setContent(data.unit)
        holder.workOrderStatus.setContent(data.status)
        holder.workOrderActivity.setContent(data.activity)
        holder.workOrderMaintLevel.setContent(data.maintenance)
        holder.workOrderNotes.setContent(data.notes)
        holder.itemView.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_workSelection_to_workPanel)
        }
    }

    override fun getItemCount() = workOrders.size
}