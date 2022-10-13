package com.product.eamfieldaccess.workpanel.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.util.TestData
import com.product.eamfieldaccess.workpanel.tabs.checklist.CheckListMainAdapter


class CheckListFragment : Fragment() {
    private lateinit var checkList: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkList = view.findViewById(R.id.rv_check_list)
        val adapter = CheckListMainAdapter(TestData.CHECKLISTS)
        checkList.adapter = adapter
        checkList.layoutManager = LinearLayoutManager(activity)

    }
}