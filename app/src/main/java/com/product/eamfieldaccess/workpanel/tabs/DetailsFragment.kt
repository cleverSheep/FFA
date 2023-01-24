package com.product.eamfieldaccess.workpanel.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.product.eamfieldaccess.databinding.FragmentDetailsBinding
import com.product.eamfieldaccess.workselection.WorkOrderViewModel


class DetailsFragment : Fragment() {
    private lateinit var model: WorkOrderViewModel
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        model = ViewModelProvider(requireActivity())[WorkOrderViewModel::class.java]
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.currentWorkOrderExtension.observe(viewLifecycleOwner) {
            binding.edittextWorkOrder.hint = it.workOrder
            binding.edittextWorkSite.hint = it.site
            binding.edittextUnit.hint = it.unit
            binding.edittextOpen.hint = it.dateOpened
            binding.edittextRequester.hint = it.requester
            binding.edittextActivityReason.hint = it.activity
            binding.edittextStandardTaskMaintLevel.hint = it.standardTask
            binding.edittextNotes.hint = it.notes
        }
    }
}