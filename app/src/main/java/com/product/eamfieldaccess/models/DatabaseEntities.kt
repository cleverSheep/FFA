package com.product.eamfieldaccess.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE


// Parent entity (Employee)
// TODO: make the uuid the PrimaryKey
@Entity(tableName = "employee_table")
class EmployeeEntity(
    @ColumnInfo(name = "employeeName") val employeeName: String,
    @ColumnInfo(name = "uuid") val uuid: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

// Child of Employee (Work Order)
@Entity(
    tableName = "workOrder",
    foreignKeys = [ForeignKey(
        entity = EmployeeEntity::class,
        parentColumns = ["id"],
        childColumns = ["employeeId"],
        onDelete = CASCADE
    )]
)
class WorkOrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "site") val site: String,
    @ColumnInfo(name = "employeeId") val employeeId: String,
) {
    @Ignore
    var workTasks: List<WorkTask>? = null
}

// Child of Work Order (Work Task)
@Entity(
    tableName = "workTask",
    foreignKeys = [ForeignKey(
        entity = WorkOrderEntity::class,
        parentColumns = ["id"],
        childColumns = ["workOrderId"],
        onDelete = CASCADE
    )]
)
class WorkTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "workOrderId") val workOrderId: String,
    @ColumnInfo(name = "category") val category: String,
)

// Child of Work Order (Work Labor)
@Entity(
    tableName = "workLabor",
    foreignKeys = [ForeignKey(
        entity = WorkOrderEntity::class,
        parentColumns = ["id"],
        childColumns = ["workOrderId"],
        onDelete = CASCADE
    )]
)
class WorkLaborEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "workOrderId") val workOrderId: String,
    @ColumnInfo(name = "workTaskID") val workTaskID: String,
)

// Child of Work Order (Check List)
@Entity(
    tableName = "checkList",
    foreignKeys = [ForeignKey(
        entity = WorkOrderEntity::class,
        parentColumns = ["id"],
        childColumns = ["workOrderId"],
        onDelete = CASCADE
    )]
)
class CheckListEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "workOrderId") val workOrderId: String,
) {
    @Ignore
    private var items: List<Item>? = null
}

// Child of Check List (Check List Item)
@Entity(
    tableName = "checkListItem",
    foreignKeys = [ForeignKey(
        entity = CheckListEntity::class,
        parentColumns = ["id"],
        childColumns = ["checkListId"],
        onDelete = CASCADE
    )]
)
class CheckListItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "lineText") val lineText: String,
    @ColumnInfo(name = "checkListId") val checkListId: String,
)