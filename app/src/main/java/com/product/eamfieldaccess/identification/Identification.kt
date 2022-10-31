package com.product.eamfieldaccess.identification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.util.TestData

class Identification : Fragment() {
    private lateinit var employees: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_identification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        employees = view.findViewById(R.id.rv_employees)
        val adapter = IdentificationAdapter(TestData.EMPLOYEES)
        employees.adapter = adapter
        employees.layoutManager = LinearLayoutManager(activity)
    }
}