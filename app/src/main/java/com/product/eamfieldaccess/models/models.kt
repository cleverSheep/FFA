package com.product.eamfieldaccess.models

import java.io.Serializable

// Response after fetching login auth code
data class UserAuth(val authorization: String)

data class Employee(
    val employeeName: String,
    val uuid: String,
    val workOrders: List<WorkOrder>
)

data class Employees(val employees: List<Employee>)

data class WorkOrder(
    val id: String,
    val site: String,
    var employeeUUID: String,
    val employeeName: String,
    val location: String,
    val unit: String,
    val workOrder: String,
    val status: String,
    val activity: String?,
    val dateOpened: String,
    val requestor: String?,
    val standardTask: String?,
    var workTasks: ArrayList<WorkTask>?,
    var workLabor: ArrayList<WorkLabor>?,
    val checkLists: List<CheckList>?
)

data class WorkOrderExtension(
    val id: String,
    val site: String,
    var employeeUUID: String,
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
    var workTasks: ArrayList<WorkTaskExtension>,
    var workLabor: ArrayList<WorkLaborExtension>,
    val startTime: String?,
    val endTime: String?,
    val checkLists: List<CheckList>
) : Serializable

data class WorkTask(
    val workOrderId: String,
    val workTaskID: String,
    val category: String,
    val employeeId: String,
    val code: String,
    val description: String?,
    val notes: String?
)

data class WorkTaskExtension(
    var workOrderId: String,
    val category: String,
    var employeeId: String,
    val code: String,
    val description: String?,
    val notes: String,
    val startTime: String?,
    val endTime: String?,
    val totalTime: String?,
    var attachmentLabel: String = ""
) : Serializable

data class WorkLabor(
    val workOrderId: String,
    val workTaskID: String,
    val employeeId: String,
    val workTaskCode: String,
    val employeeName: String,
    val dateOpened: String,
    val startTime: String,
    val endTime: String?,
    val totalTime: String?,
    val system: String,
    val description: String?
)

data class WorkLaborExtension(
    val workOrderId: String,
    val employeeId: String,
    val workTaskCode: String,
    val employeeName: String,
    var isOnBreak: Boolean = false,
    var totalBreakTime: Long = 0,
    var timeBreakTaken: Long = 0,
    val date: String,
    var startTime: String?,
    var endTime: String?,
    var totalTime: String?,
    val system: String,
    val description: String?
) : Serializable

data class CheckList(
    val title: String,
    val items: List<Item>
) : Serializable

data class Item(
    val lineText: String
)
// -------------------- //

data class TaskTime(
    val workOrderId: String,
    val workTaskCode: String,
    val employeeId: String,
    val startTime: String = "---",
    val endTime: String = "---",
    val totalTime: String = "---"
)
