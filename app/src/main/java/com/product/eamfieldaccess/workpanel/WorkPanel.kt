package com.product.eamfieldaccess.workpanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R

class WorkPanel : AbstractListDetailFragment() {

    override fun onCreateListPaneView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_work_panel, container, false)
    }

    override fun onCreateDetailPaneNavHostFragment(): NavHostFragment {
        return NavHostFragment.create(R.navigation.work_panel_nav)
    }

    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onListPaneViewCreated(view, savedInstanceState)
        val recyclerView = view as RecyclerView
        recyclerView.adapter = DataAdapter(map.keys.toTypedArray()) {
            map[it]?.let { destId -> openDetails(destId) }
        }
    }

    private fun openDetails(destinationId: Int) {
        val detailNavController = detailPaneNavHostFragment.navController
        detailNavController.navigate(
            destinationId,
            null,
            NavOptions.Builder()
                .setPopUpTo(detailNavController.graph.startDestinationId, true)
                .build()
        )
        slidingPaneLayout.open()
    }

    companion object {
        val map = mapOf(
            "details" to R.id.detailsFragment,
            "workTasks" to R.id.workTasksFragment,
            "labor" to R.id.laborFragment,
            "checklist" to R.id.checkListFragment,
            "workRequest" to R.id.workRequestFragment
        )
    }
}