package com.product.eamfieldaccess.models

data class Employee(
    val name: String,
    val id: String,
    val sites: List<Site>
)

data class Site(
    val name: String,
    val id: String,
    val workOrders: List<WorkOrder>
)

data class WorkOrder(
    val id: String,
    val site: String,
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
    val workTasks: List<WorkTask>,
    val labor: List<Labor>,
    val startTime: String,
    val endTime: String,
    val checkList: List<CheckList>
)

data class WorkTask(
    val workOrderId: String,
    val category: String,
    val code: String,
    val description: String,
    val notes: String,
    val startTime: String = "",
    val endTime: String = "",
    val totalTime: Int = 0
)

data class TaskTime(
    val workOrderId: String,
    val workTaskCode: String,
    val startTime: String,
    val endTime: String,
    val totalTime: Int
)

data class Labor(
    val workOrderId: String,
    val employeeId: String,
    val workTaskCode: String,
    val employeeName: String,
    val date: String,
    var startTime: String,
    var endTime: String,
    var totalTime: Int,
    val system: String,
    val description: String
)

data class CheckList(
    val title: String,
    val items: List<String>
)
// -------------------- //
