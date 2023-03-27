package com.product.eamfieldaccess.models

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

interface JsonParser {
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj: T, type: Type): String?
}

class GsonParser(
    private val gson: Gson
) : JsonParser {
    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json, type)
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        return gson.toJson(obj, type)
    }
}

@ProvidedTypeConverter
class EmployeeDataConverter(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun toWorkOrderJson(workOrders: List<WorkOrder>) : String {
        return jsonParser.toJson(
            workOrders,
            object : TypeToken<List<WorkOrder>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromWorkOrderJson(json: String): List<WorkOrder> {
        return jsonParser.fromJson<List<WorkOrder>>(
            json,
            object : TypeToken<List<WorkOrder>>(){}.type
        ) ?: emptyList()
    }
}