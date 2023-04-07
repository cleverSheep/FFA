package com.product.eamfieldaccess.identification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.MainApplication
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.EmployeeExtension
import com.product.eamfieldaccess.util.Utils
import com.product.eamfieldaccess.util.observeOnce
import com.product.eamfieldaccess.viewmodels.EmployeeViewModel
import com.product.eamfieldaccess.viewmodels.EmployeeViewModelFactory
import org.json.JSONObject

class Identification : Fragment() {
    private lateinit var employees: RecyclerView
    private val employeeViewModel: EmployeeViewModel by viewModels {
        EmployeeViewModelFactory((activity?.application as MainApplication).repository)
    }

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
        val adapter = IdentificationAdapter()
        fetchAuthEmployee()
        fetchAllEmployees(adapter)
        employees.adapter = adapter
        employees.layoutManager = LinearLayoutManager(activity)
    }

    /**
     * TODO: Replace the sample datqa
     */
    fun fetchAuthEmployee() {
        employeeViewModel.getAuthEmployee(
            authorization = "1XII/y1Mdh4MHVSk9iKpZbWdVsjjjvi0jRtDm4rzKPUiyjtF07kWbmCmMxr" +
                    "BKMJ/E1jAy7pY7+0cx/V2zeHAKeheB9L1uerdHImfl12bacCMpGrkNnmG/2Wl+6cpsqKpdUTrGOIO/TJf" +
                    "jsHAjEL+wAMRbMCJUStM6l+f/IJMCvk7h+M2UZRjxhtaHBGGZqBZ9E" +
                    "ZswfDv7I0hmBGd7pFMbZrcESCzSpOCh0txT2hmL3XvHG2fnokQlrRYPgU/UztF",
            command = "ffa.getauthenticatedemployee",
            data = JSONObject().put("timestamp", "")
        )
        employeeViewModel.authEmployeeId.observe(viewLifecycleOwner) {
            Utils.AUTH_EMPLOYEE = it
        }

    }

    /**
     * TODO: Replace the sample datq with login credentials
     */
    fun fetchAllEmployees(adapter: IdentificationAdapter) {
        employeeViewModel.getAllEmployees(
            authorization = "1XII/y1Mdh4MHVSk9iKpZbWdVsjjjvi0jRtDm4rzKPUiyjtF07kWbmCmMxr" +
                    "BKMJ/E1jAy7pY7+0cx/V2zeHAKeheB9L1uerdHImfl12bacCMpGrkNnmG/2Wl+6cpsqKpdUTrGOIO/TJf" +
                    "jsHAjEL+wAMRbMCJUStM6l+f/IJMCvk7h+M2UZRjxhtaHBGGZqBZ9E" +
                    "ZswfDv7I0hmBGd7pFMbZrcESCzSpOCh0txT2hmL3XvHG2fnokQlrRYPgU/UztF",
            command = "ffa.getallemployees",
            data = JSONObject().put("timestamp", "")
        )
        employeeViewModel.employees.observeOnce(viewLifecycleOwner) { employees ->
            val filteredEmployees = employees.map {
                EmployeeExtension(it.employee.uuid, it.employee.employeeName, it)
            }
            filteredEmployees.filter {
                it.uuid != Utils.AUTH_EMPLOYEE?.uuid
            }
            adapter.addEmployees(filteredEmployees)
        }
    }

}