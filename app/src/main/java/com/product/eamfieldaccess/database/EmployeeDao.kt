package com.product.eamfieldaccess.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.product.eamfieldaccess.models.EmployeeEntity
import com.product.eamfieldaccess.models.EmployeeWorkOrderDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee_table")
    fun getEmployees(): Flow<List<EmployeeWorkOrderDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employeeEntity: EmployeeEntity)
}