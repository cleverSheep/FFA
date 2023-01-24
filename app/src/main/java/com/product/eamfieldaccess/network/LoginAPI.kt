package com.product.eamfieldaccess.network

import com.google.gson.JsonObject
import com.product.eamfieldaccess.models.UserAuth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginAPI {

    @Headers("Content-Type: text/plain")
    @POST("Login")
    fun loginUser(@Body userAuth: JsonObject): Call<UserAuth>
}