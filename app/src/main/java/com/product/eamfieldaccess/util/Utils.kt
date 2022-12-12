package com.product.eamfieldaccess.util

import java.util.*

class Utils {
    companion object {
        val TASK_RUNNING = mutableMapOf<String, Boolean>()
        val TASK_START_TIME = mutableMapOf<String, Date>()
        var CURRENT_EMPLOYEE_ADDED_TASK = mutableSetOf<String>()
        val TASK_PAUSED = mutableMapOf<String, PausedTime?>()
    }
}

data class PausedTime(
    var isOnBreak: Boolean,
    var totalBreakTime: Long,
    var timeBreakTaken: Date,
    var timeTaskResumed: Date?
)