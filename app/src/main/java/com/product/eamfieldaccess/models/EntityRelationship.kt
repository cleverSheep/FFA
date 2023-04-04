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
)

class ChecklistWorkOrder(
    @Embedded var workOrder: WorkOrderExtension,
    @Relation(
        parentColumn = "id",
        entityColumn = "workOrderId",
        entity = CheckList::class
    ) var checkLists: List<ItemChecklistMap>
)

class WorkTaskWorkOrder(
    @Embedded var workOrder: WorkOrderExtension,
    @Relation(
        parentColumn = "id",
        entityColumn = "workOrderId",
        entity = WorkTaskExtension::class
    ) var workTasks: List<WorkTaskExtension>
)

class WorkLaborWorkOrder(
    @Embedded var workOrder: WorkOrderExtension,
    @Relation(
        parentColumn = "id",
        entityColumn = "workOrderId",
        entity = WorkLaborExtension::class
    ) var workLabor: List<WorkLaborExtension>
)

class EmployeeWorkOrderDetail(
    @Embedded var employee: Employee,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "employeeUUID",
        entity = WorkOrderExtension::class
    ) var workOrders: List<WorkOrderExtension>,
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
)
