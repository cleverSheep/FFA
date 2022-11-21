package com.product.eamfieldaccess.util

import com.product.eamfieldaccess.models.Employee
import com.product.eamfieldaccess.util.TestData.Companion.AUTHENTICATED_EMPLOYEE
import java.util.*

class Utils {
    companion object {
        val TASK_RUNNING = mutableMapOf<String, Boolean>()
        val TASK_START_TIME = mutableMapOf<String, Date>()
        val MAPPED_EMPLOYEES = mutableMapOf<String, Employee>()
        var CURRENT_EMPLOYEE = AUTHENTICATED_EMPLOYEE
        var CURRENT_EMPLOYEE_ADDED_TASK = mutableSetOf<String>()
    }
}