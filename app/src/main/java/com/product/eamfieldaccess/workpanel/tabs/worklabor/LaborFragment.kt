package com.product.eamfieldaccess.workpanel.tabs.worklabor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.product.eamfieldaccess.databinding.FragmentLaborBinding
import com.product.eamfieldaccess.workselection.WorkOrderViewModel

class LaborFragment : Fragment() {
    private lateinit var binding: FragmentLaborBinding
    private lateinit var model: WorkOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        model = ViewModelProvider(requireActivity())[WorkOrderViewModel::class.java]
        binding = FragmentLaborBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WorkLaborAdapter()

        model.currentWorkOrderExtension.observe(viewLifecycleOwner) {
            binding.rvLabor.adapter = adapter
            adapter.addLaborItems(it.workLabor)
            binding.rvLabor.layoutManager = LinearLayoutManager(activity)
        }

        model.currentTime.observe(viewLifecycleOwner) {
            adapter.updateTaskTime(it)
        }
    }
}