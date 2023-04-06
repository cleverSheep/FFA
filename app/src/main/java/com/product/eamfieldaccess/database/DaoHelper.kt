package com.product.eamfieldaccess.database

import com.product.eamfieldaccess.models.*
import com.product.eamfieldaccess.util.Utils.Companion.workOrderToWorkOrderExtension

class DaoHelper(database: EmployeeRoomDatabase) {
    private val employeeDao: EmployeeDao

    init {
        employeeDao = database.employeeDao()
    }

    suspend fun saveEmployee(employee: Employee) {
        employeeDao.addEmployee(employee)
        saveWorkOrders(employee.uuid, employee.workOrders!!)
    }

    // save work orders for each employee
    suspend fun saveWorkOrders(employeeId: String, workOrders: List<WorkOrder>) {
        val workOrderExtensions = workOrderToWorkOrderExtension(workOrders)
        workOrderExtensions.forEach { workOrder ->
            workOrder.employeeUUID = employeeId
            employeeDao.addWorkOrder(workOrder)
            insertWorkTasksForWorkOrder(workOrder.id, workOrder.workTasks!!)
            insertWorkLaborForWorkOrder(workOrder.id, workOrder.workLabor!!)
            insertCheckListsForWorkOrder(workOrder.id, workOrder.checkLists)
        }
    }

    suspend fun insertWorkTasksForWorkOrder(
        workOrderId: String,
        workTasks: List<WorkTaskExtension>
    ) {
        workTasks.forEach { workTask ->
            workTask.workOrderId = workOrderId
        }
        employeeDao.addWorkTasks(workTasks)
    }

    suspend fun insertWorkLaborForWorkOrder(
        workOrderId: String,
        workLabor: List<WorkLaborExtension>
    ) {
        workLabor.forEach { labor ->
            labor.workOrderId = workOrderId
        }
        employeeDao.addWorkLabor(workLabor)
    }

    suspend fun insertCheckListsForWorkOrder(workOrderId: String, checkLists: List<CheckList>) {
        checkLists.forEach { checkList ->
            checkList.workOrderId = workOrderId
            val checkListId = employeeDao.addCheckList(checkList)
            inserItemsForCheckList(checkListId, checkList.items!!)
        }
    }

    suspend fun inserItemsForCheckList(checkListId: Long, items: List<Item>) {
        items.forEach { item ->
            item.checkListId = checkListId.toString()
        }
        employeeDao.addCheckListItems(items)
    }
}