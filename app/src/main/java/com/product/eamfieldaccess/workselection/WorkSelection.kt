package com.product.eamfieldaccess.workselection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.identification.IdentificationAdapter
import com.product.eamfieldaccess.models.WorkOrderExtension

class WorkSelection : Fragment() {
    private lateinit var workOrders: RecyclerView
    private lateinit var model: WorkOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        model = ViewModelProvider(requireActivity())[WorkOrderViewModel::class.java]
        return inflater.inflate(R.layout.fragment_work_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workOrders = view.findViewById(R.id.rv_work_orders)
        val fetchedWorkOrders =
            requireArguments().getSerializable("work_orders") as IdentificationAdapter.WorkOrders
        val adapter = WorkSelectionAdapter(fetchedWorkOrders.orders, this::onWorkOrderSelected)
        workOrders.adapter = adapter
        workOrders.layoutManager = LinearLayoutManager(activity)
    }

    fun onWorkOrderSelected(workOrderExtension: WorkOrderExtension) {
        model.currentWorkOrderExtension.postValue(workOrderExtension)
    }
}