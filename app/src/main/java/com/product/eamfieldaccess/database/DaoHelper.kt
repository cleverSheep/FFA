package com.product.eamfieldaccess.database

import com.product.eamfieldaccess.models.*
import com.product.eamfieldaccess.util.Utils.Companion.mapWorkOrder

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
    suspend fun saveWorkOrders(employeeId: String, workOrders: ArrayList<WorkOrder>) {
        val workOrderExtensions = mapWorkOrder(workOrders)
        workOrderExtensions.forEach { workOrder ->
            workOrder.employeeUUID = employeeId
            employeeDao.addWorkOrder(workOrder)
            insertWorkTasksForWorkOrder(workOrder.id, workOrder.workTasks!!)
            insertWorkLaborForWorkOrder(workOrder.id, workOrder.workLabor!!)
            insertCheckListsForWorkOrder(workOrder.id, workOrder.checkLists!!)
        }
    }

    suspend fun insertWorkTasksForWorkOrder(
        workOrderId: String,
        workTasks: ArrayList<WorkTaskExtension>
    ) {
        workTasks.forEach { workTask ->
            workTask.workOrderId = workOrderId
        }
        employeeDao.addWorkTasks(workTasks)
    }

    suspend fun insertWorkLaborForWorkOrder(
        workOrderId: String,
        workLabor: ArrayList<WorkLaborExtension>
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