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
    @Embedded var checkList: CheckList,
    @Relation(
        parentColumn = "id",
        entityColumn = "checkListId",
        entity = Item::class
    ) var items: List<Item>
) {
    fun fetchCheckList(): CheckList {
        return checkList
    }

    fun initCheckList(checkList: CheckList) {
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
    @Embedded var workOrder: WorkOrderExtension,
    @Relation(
        parentColumn = "id",
        entityColumn = "workOrderId",
        entity = CheckList::class
    ) var checkLists: List<ItemChecklistMap>
) {

    fun fetchWorkOrder(): WorkOrderExtension {
        return workOrder
    }

    fun initWorkOrder(workOrder: WorkOrderExtension) {
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
    @Embedded var workOrder: WorkOrderExtension,
    @Relation(
        parentColumn = "id",
        entityColumn = "workOrderId",
        entity = WorkTaskExtension::class
    ) var workTasks: List<WorkTaskExtension>
) {
    fun fetchWorkOrder(): WorkOrderExtension {
        return workOrder
    }

    fun initWorkOrder(workOrder: WorkOrderExtension) {
        this.workOrder = workOrder
    }

    fun fetchWorkTasks(): List<WorkTaskExtension> {
        return workTasks
    }

    fun initWorkTasks(workTasks: List<WorkTaskExtension>) {
        this.workTasks = workTasks
    }
}

class WorkLaborWorkOrder(
    @Embedded var workOrder: WorkOrderExtension,
    @Relation(
        parentColumn = "id",
        entityColumn = "workOrderId",
        entity = WorkLaborExtension::class
    ) var workLabor: List<WorkLaborExtension>
) {
    fun fetchWorkOrder(): WorkOrderExtension {
        return workOrder
    }

    fun initWorkOrder(workOrder: WorkOrderExtension) {
        this.workOrder = workOrder
    }

    fun fetchWorkLabor(): List<WorkLaborExtension> {
        return workLabor
    }

    fun initWorkLabor(workLabor: List<WorkLaborExtension>) {
        this.workLabor = workLabor
    }
}

class EmployeeWorkOrderDetail(
    @Embedded var employee: Employee,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "employeeUUID",
        entity = WorkOrderExtension::class
    ) var workLabor: List<WorkLaborWorkOrder>,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "employeeUUID",
        entity = WorkOrderExtension::class
    ) var workTasks: List<WorkTaskWorkOrder>,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "employeeUUID",
        entity = WorkOrderExtension::class
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
