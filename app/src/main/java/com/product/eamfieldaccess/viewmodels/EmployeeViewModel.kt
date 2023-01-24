package com.product.eamfieldaccess.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.product.eamfieldaccess.repositories.EmployeeRepository
import org.json.JSONObject

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private var employeeRepository: EmployeeRepository = EmployeeRepository()

    val authEmployee = employeeRepository.authEmployee
    val allEmployees = employeeRepository.allEmployees

    fun getAuthEmployee(
        authorization: String,
        command: String,
        data: JSONObject
    ) {
        val authEmployeeJSON = JSONObject()
        authEmployeeJSON.put("authorization", authorization)
        authEmployeeJSON.put("command", command)
        authEmployeeJSON.put("data", data)
        getAuthEmployee(authEmployeeJSON)
    }

    private fun getAuthEmployee(user: JSONObject) {
        employeeRepository.fetchAuthEmployee(user)
    }

    fun getAllEmployees(
        authorization: String,
        command: String,
        data: JSONObject
    ) {
        val authEmployeeJSON = JSONObject()
        authEmployeeJSON.put("authorization", authorization)
        authEmployeeJSON.put("command", command)
        authEmployeeJSON.put("data", data)
        getAllEmployees(authEmployeeJSON)
    }

    private fun getAllEmployees(user: JSONObject) {
        employeeRepository.fetchAllEmployees(user)
    }
}