package com.product.eamfieldaccess.models

import androidx.room.Embedded
import androidx.room.Relation

/**
 *   | | | Item --> Checklist |, WorkTask, WorkLabor | --> Work Order --> Employee |
 *
 *   MANY Item(s) to ONE Checklist
 *   MANY Checklist(s), WorkTask(s), WorkLabor(s) to ONE WorkOrder
 *   MANY WorkOrder(s) to ONE Employee
 *
 *   We'll need to model this nested relationship.
 */

// All ITEMS with checkListId
class ItemChecklistMap(
    @Embedded var checkList: CheckListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "checkListId",
        entity = CheckListItemEntity::class
    ) var items: List<Item>
) {
    fun fetchCheckList(): CheckListEntity {
        return checkList
    }

    fun initCheckList(checkList: CheckListEntity) {
        this.checkList = checkList
    }

    fun fetchItems(): List<Item> {
        return items
    }

    fun initItems(items: List<Item>) {
        this.items = items
    }
}

class ChecklistWorkOrder(
    @Embedded var workOrder: WorkOrderEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "workOrderId",
        entity = CheckListEntity::class
    ) var checkLists: List<ItemChecklistMap>
) {

    fun fetchWorkOrder(): WorkOrderEntity {
        return workOrder
    }

    fun initWorkOrder(workOrder: WorkOrderEntity) {
        this.workOrder = workOrder
    }

    fun fetchCheckLists(): List<ItemChecklistMap> {
        return checkLists
    }

    fun initCheckLists(checkLists: List<ItemChecklistMap>) {
        this.checkLists = checkLists
    }
}

class WorkTaskWorkOrder(
    @Embedded var workOrder: WorkOrderEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "workOrderId",
        entity = WorkTaskEntity::class
    ) var workTasks: List<WorkTaskEntity>
) {
    fun fetchWorkOrder(): WorkOrderEntity {
        return workOrder
    }

    fun initWorkOrder(workOrder: WorkOrderEntity) {
        this.workOrder = workOrder
    }

    fun fetchWorkTasks(): List<WorkTaskEntity> {
        return workTasks
    }

    fun initWorkTasks(workTasks: List<WorkTaskEntity>) {
        this.workTasks = workTasks
    }
}

class WorkLaborWorkOrder(
    @Embedded var workOrder: WorkOrderEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "workOrderId",
        entity = WorkLaborEntity::class
    ) var workLabor: List<WorkLaborEntity>
) {
    fun fetchWorkOrder(): WorkOrderEntity {
        return workOrder
    }

    fun initWorkOrder(workOrder: WorkOrderEntity) {
        this.workOrder = workOrder
    }

    fun fetchWorkLabor(): List<WorkLaborEntity> {
        return workLabor
    }

    fun initWorkLabor(workLabor: List<WorkLaborEntity>) {
        this.workLabor = workLabor
    }
}

class EmployeeWorkOrderDetail(
    @Embedded var employee: Employee,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "employeeId",
        entity = WorkOrderEntity::class
    ) var workLabor: List<WorkLaborWorkOrder>,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "employeeId",
        entity = WorkOrderEntity::class
    ) var workTasks: List<WorkTaskWorkOrder>,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "employeeId",
        entity = WorkOrderEntity::class
    ) var workCheckLists: List<ChecklistWorkOrder>
) {
    fun fetchEmployee(): Employee {
        return employee
    }

    fun initEmployee(employee: Employee) {
        this.employee = employee
    }

    fun fetchWorkLabor(): List<WorkLaborWorkOrder> {
        return workLabor
    }

    fun initWorkLabor(workLabor: List<WorkLaborWorkOrder>) {
        this.workLabor = workLabor
    }

    fun fetchWorkTasks(): List<WorkTaskWorkOrder> {
        return workTasks
    }

    fun initWorkTasks(workTasks: List<WorkTaskWorkOrder>) {
        this.workTasks = workTasks
    }

    fun fetchWorkCheckLists(): List<ChecklistWorkOrder> {
        return workCheckLists
    }

    fun initWorkCheckLists(workCheckLists: List<ChecklistWorkOrder>) {
        this.workCheckLists = workCheckLists
    }

}
