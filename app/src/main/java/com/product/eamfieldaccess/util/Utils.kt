package com.product.eamfieldaccess.util

import androidx.lifecycle.LiveData
import java.util.*

class Utils {
    companion object {
        val TASK_RUNNING = mutableMapOf<String, Boolean>()
        val TASK_START_TIME = mutableMapOf<String, Date>()
    }
}