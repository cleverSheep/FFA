package com.product.eamfieldaccess.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.product.eamfieldaccess.database.EmployeeDao
import com.product.eamfieldaccess.models.Employee
import com.product.eamfieldaccess.models.EmployeeEntity
import com.product.eamfieldaccess.models.EmployeeWorkOrderDetail
import com.product.eamfieldaccess.models.Employees
import com.product.eamfieldaccess.network.APIClient
import com.product.eamfieldaccess.network.EmployeeAPI
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    private val retrofit = APIClient.getClient().create(EmployeeAPI::class.java)

    private val _authEmployee = MutableLiveData<Employee?>()
    private val _allEmployees = MutableLiveData<Employees?>()

    val authEmployee = _authEmployee
    val allEmployees = _allEmployees

    val employees: Flow<List<EmployeeWorkOrderDetail>> = employeeDao.getEmployees()

    fun fetchAuthEmployee(user: JSONObject) {
        var requestBody: RequestBody? = null
        try {
            requestBody = JSONObject(user.toString()).toString()
                .toRequestBody("text/plain".toMediaTypeOrNull())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        retrofit.getAuthEmployee(requestBody!!).enqueue(
            object : Callback<Employee> {
                override fun onResponse(call: Call<Employee>?, response: Response<Employee>?) {
                    val userAuth = response?.body()
                    _authEmployee.postValue(userAuth)
                }

                override fun onFailure(call: Call<Employee>?, t: Throwable?) {
                    _authEmployee.postValue(null)
                }
            }
        )
    }

    fun fetchAllEmployees(user: JSONObject) {
        var requestBody: RequestBody? = null
        try {
            requestBody = JSONObject(user.toString()).toString()
                .toRequestBody("text/plain".toMediaTypeOrNull())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        retrofit.getAllEmployees(requestBody!!).enqueue(
            object : Callback<Employees> {
                override fun onResponse(call: Call<Employees>?, response: Response<Employees>?) {
                    val userAuth = response?.body()
                    _allEmployees.postValue(userAuth)
                }

                override fun onFailure(call: Call<Employees>?, t: Throwable?) {
                    _allEmployees.postValue(null)
                }
            }
        )
    }

    @WorkerThread
    suspend fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }
}