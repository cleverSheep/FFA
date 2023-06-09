package com.product.eamfieldaccess.viewmodels

import androidx.lifecycle.*
import com.product.eamfieldaccess.models.AuthEmployee
import com.product.eamfieldaccess.models.EmployeeWorkOrderDetail
import com.product.eamfieldaccess.repositories.EmployeeRepository
import org.json.JSONObject

class EmployeeViewModel(private val employeeRepository: EmployeeRepository) : ViewModel() {

    val employees: LiveData<List<EmployeeWorkOrderDetail>> = employeeRepository.employees.asLiveData()
    val authEmployeeId: LiveData<AuthEmployee> = employeeRepository.authEmployeeId.asLiveData()

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

class EmployeeViewModelFactory(private val repository: EmployeeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(  EmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}