package com.product.eamfieldaccess.util

import com.product.eamfieldaccess.models.*
import java.util.*
import kotlin.collections.ArrayList

class Utils {
    companion object {
        val TASK_RUNNING = mutableMapOf<String, Boolean>()
        val TASK_START_TIME = mutableMapOf<String, Date>()
        var CURRENT_EMPLOYEE_ADDED_TASK = mutableSetOf<String>()
        val TASK_PAUSED = mutableMapOf<String, PausedTime?>()
        var AUTH_EMPLOYEE: Employee? = null

        fun workOrderToWorkOrderExtension(workOrders: List<WorkOrder>): ArrayList<WorkOrderExtension> {
            val workOrderExtensions = ArrayList<WorkOrderExtension>()
            val allWorkTasks = ArrayList<WorkTaskExtension>()
            val allWorkLabor = ArrayList<WorkLaborExtension>()

            workOrders.forEach { workOrder ->
                workOrder.workTasks?.let {
                    it.forEach { workTask ->
                        val workTask = WorkTaskExtension(
                            workOrderId = workTask.workOrderId,
                            category = workTask.category,
                            employeeId = workTask.employeeId,
                            code = workTask.code,
                            description = workTask.description ?: "---",
                            notes = workTask.notes ?: "---",
                            startTime = "---",
                            endTime = "---",
                            totalTime = "---",
                            attachmentLabel = "---"
                        )
                        allWorkTasks.add(workTask)
                    }
                }
                workOrder.workLabor?.let {
                    it.forEach { labor ->
                        val workLabor = WorkLaborExtension(
                            workOrderId = labor.workOrderId,
                            employeeId = labor.employeeId,
                            workTaskCode = labor.workTaskCode,
                            employeeName = labor.employeeName,
                            date = labor.dateOpened,
                            startTime = labor.startTime,
                            endTime = labor.endTime,
                            totalTime = labor.totalTime,
                            system = labor.system,
                            description = labor.description
                        )
                        allWorkLabor.add(workLabor)
                    }
                }
                val workOrderExtension = WorkOrderExtension(
                    id = workOrder.id,
                    site = workOrder.site,
                    employeeUUID = workOrder.employeeUUID,
                    location = workOrder.location,
                    unit = workOrder.unit,
                    workOrder = workOrder.workOrder,
                    status = workOrder.status,
                    activity = workOrder.activity ?: "---",
                    maintenance = "---",
                    dateOpened = workOrder.dateOpened,
                    requester = workOrder.requestor ?: "---",
                    notes = "---",
                    standardTask = workOrder.standardTask ?: "---",
                    startTime = "",
                    endTime = ""
                )
                workOrderExtension.workTasks = allWorkTasks
                workOrderExtension.workLabor = allWorkLabor
                workOrderExtension.checkLists =
                    workOrder.checkLists?.let { ArrayList(it) } ?: ArrayList()
                workOrderExtensions.add(workOrderExtension)
            }
            return workOrderExtensions
        }
    }
}

data class PausedTime(
    var isOnBreak: Boolean,
    var totalBreakTime: Long,
    var timeBreakTaken: Date,
    var timeTaskResumed: Date?
)