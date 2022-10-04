package com.product.eamfieldaccess.workselection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.util.TestData

class WorkSelection : Fragment() {
    private lateinit var workOrders: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workOrders = view.findViewById(R.id.rv_work_orders)

        val adapter = WorkSelectionAdapter(TestData.WORKORDERS)
        workOrders.adapter = adapter
        workOrders.layoutManager = LinearLayoutManager(activity)


    }
}