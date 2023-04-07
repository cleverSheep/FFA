package com.product.eamfieldaccess.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.product.eamfieldaccess.models.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    fun getEmployees(): Flow<List<EmployeeWorkOrderDetail>>

    @Query("SELECT * FROM auth_employee")
    fun getAuthEmployeeID(): Flow<AuthEmployee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmployee(employee: Employee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAuthEmployeeID(authEmployee: AuthEmployee)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWorkOrder(workOrder: WorkOrderExtension)

    /**
     * TODO: create updateWorkOrder function that uses OnConflictStrategy.REPLACE
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWorkOrders(workOrder: ArrayList<WorkOrderExtension>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWorkTasks(workTasks: List<WorkTaskExtension>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWorkLabor(workLabor: List<WorkLaborExtension>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCheckLists(checklists: List<CheckList>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCheckList(checklists: CheckList): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCheckListItems(items: List<Item>)

/*
    @Query("DELETE FROM employees")
    suspend fun deleteEmployees()
*/
}