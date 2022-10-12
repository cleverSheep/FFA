package com.product.eamfieldaccess.workpanel.tabs.worklabor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.util.TestData
import com.product.eamfieldaccess.workpanel.tabs.worktasks.WorkTaskAdapter

class LaborFragment : Fragment() {
    private lateinit var labor: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_labor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        labor = view.findViewById(R.id.rv_labor)

        val adapter = WorkLaborAdapter(TestData.LABOR)
        labor.adapter = adapter
        labor.layoutManager = LinearLayoutManager(activity)

    }
}