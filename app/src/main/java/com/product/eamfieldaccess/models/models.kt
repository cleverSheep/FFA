package com.product.eamfieldaccess.models

import java.io.Serializable


data class Employee(
    val name: String,
    val id: String,
    val sites: List<Site>
)

data class WorkOrder(
    val id: String,
    val site: String,
    var employeeId: String,
    val location: String,
    val unit: String,
    val workOrder: String,
    val status: String,
    val activity: String,
    val maintenance: String,
    val dateOpened: String,
    val requester: String,
    val notes: String,
    val standardTask: String,
    var workTasks: ArrayList<WorkTask>,
    var labor: ArrayList<Labor>,
    val startTime: String,
    val endTime: String,
    val checkList: List<CheckList>
) : Serializable

data class WorkTask(
    var workOrderId: String,
    val category: String,
    var employeeId: String,
    val code: String,
    val description: String,
    val notes: String,
    val startTime: String = "",
    val endTime: String = "",
    val totalTime: String = ""
) : Serializable

data class Labor(
    val workOrderId: String,
    val employeeId: String,
    val workTaskCode: String,
    val employeeName: String,
    var isOnBreak: Boolean = false,
    var totalBreakTime: Long = 0,
    var timeBreakTaken: Long = 0,
    val date: String,
    var startTime: String,
    var endTime: String,
    var totalTime: String,
    val system: String,
    val description: String
) : Serializable

data class CheckList(
    val title: String,
    val items: List<String>
) : Serializable
// -------------------- //

data class Site(
    val name: String,
    val id: String,
    val workOrders: List<WorkOrder>
)

data class TaskTime(
    val workOrderId: String,
    val workTaskCode: String,
    val employeeId: String,
    val startTime: String = "---",
    val endTime: String = "---",
    val totalTime: String = "---"
)
