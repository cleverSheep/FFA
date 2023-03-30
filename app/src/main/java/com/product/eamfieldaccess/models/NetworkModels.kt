package com.product.eamfieldaccess.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

// Response after fetching login auth code
data class UserAuth(val authorization: String)

/**
 * There is a one-to-many relationship between Employee and WorkOrder;
 * one employee is mapped to many work orders. A work order contains MANY
 * work tasks, work labor, and check lists. Therefore, there is a nested mapping.
 *
 *                      EMPLOYEE (o)
 *                          |
 *                          |
 *                          V
 *                        WORK ORDER (m)
 *                       /      |       \
 *                WORK TASKS(m) WORK LABOR(m)  CHECKLISTS(m)
 *
 * We'll need to model this nested relationship.
 *
 */

@Entity(tableName = "employees")
class Employee(
    @PrimaryKey val uuid: String,
    val employeeName: String
) {
    @Ignore
    var workOrders: ArrayList<WorkOrder>? = null
}

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

@Entity(
    tableName = "workOrders",
    foreignKeys = [ForeignKey(
        entity = Employee::class,
        parentColumns = ["uuid"],
        childColumns = ["employeeUUID"],
        onDelete = ForeignKey.CASCADE
    )]
)
class WorkOrderExtension(
    @PrimaryKey val id: String,
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
    val startTime: String?,
    val endTime: String?,
) : Serializable {
    @Ignore
    var workTasks: ArrayList<WorkTaskExtension>? = null

    @Ignore
    var workLabor: ArrayList<WorkLaborExtension>? = null

    @Ignore
    var checkLists: List<CheckList>? = null
}

class WorkTask(
    val workOrderId: String,
    val workTaskID: String,
    val category: String,
    val employeeId: String,
    val code: String,
    val description: String?,
    val notes: String?
)

@Entity(
    tableName = "workTasks",
    foreignKeys = [ForeignKey(
        entity = WorkOrderExtension::class,
        parentColumns = ["id"],
        childColumns = ["workOrderId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class WorkTaskExtension(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
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

@Entity(
    tableName = "workLabor",
    foreignKeys = [ForeignKey(
        entity = WorkOrderExtension::class,
        parentColumns = ["id"],
        childColumns = ["workOrderId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class WorkLaborExtension(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var workOrderId: String,
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

//TODO: update API to return work order ID associated with a CheckList
@Entity(
    tableName = "checkList",
    foreignKeys = [ForeignKey(
        entity = WorkOrderExtension::class,
        parentColumns = ["id"],
        childColumns = ["workOrderId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CheckList(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    var workOrderId: String? = null
) : Serializable {
    @Ignore
    var items: List<Item>? = null
}

@Entity(
    tableName = "checkListItems",
    foreignKeys = [ForeignKey(
        entity = CheckList::class,
        parentColumns = ["id"],
        childColumns = ["checkListId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val lineText: String,
    var checkListId: String? = null
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
