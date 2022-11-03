package com.product.eamfieldaccess.workpanel.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.product.eamfieldaccess.databinding.FragmentCheckListBinding
import com.product.eamfieldaccess.models.CheckList
import com.product.eamfieldaccess.workpanel.tabs.checklist.CheckListMainAdapter
import com.product.eamfieldaccess.workselection.WorkOrderViewModel


class CheckListFragment : Fragment() {
    private lateinit var binding: FragmentCheckListBinding
    private lateinit var model: WorkOrderViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        model = ViewModelProvider(requireActivity())[WorkOrderViewModel::class.java]
        binding = FragmentCheckListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.currentWorkOrder.observe(viewLifecycleOwner) {
            val adapter = CheckListMainAdapter(it.checkList as ArrayList<CheckList>)
            binding.rvCheckList.adapter = adapter
            binding.rvCheckList.layoutManager = LinearLayoutManager(activity)
        }
    }
}