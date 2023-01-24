package com.product.eamfieldaccess.network

import com.product.eamfieldaccess.models.Employee
import com.product.eamfieldaccess.models.Employees
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface EmployeeAPI {

    @Headers("Content-Type: text/plain")
    @POST("Process")
    fun getAuthEmployee(@Body requestbody: RequestBody): Call<Employee>

    @Headers("Content-Type: text/plain")
    @POST("Process")
    fun getAllEmployees(@Body requestbody: RequestBody): Call<Employees>


}