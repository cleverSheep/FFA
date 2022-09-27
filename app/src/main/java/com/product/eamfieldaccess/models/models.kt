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
    val site: String,
    val location: String,
    val unit: String,
    val workOrder: String,
    val status: String,
    val activity: String,
    val maintenance: String,
    val notes: String,
    val standardTask: String,
    val workTasks: List<WorkTask>,
    val labor: List<Labor>
)

data class WorkTask(
    val category: String,
    val code: String,
    val description: String,
    val notes: String
)

data class Labor(
    val employeeName: String,
    val date: String,
    val start: Int,
    val stop: Int,
    val time: String,
    val system: String,
    val description: String
)
// -------------------- //
