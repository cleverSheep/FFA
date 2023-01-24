package com.product.eamfieldaccess.workselection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.product.eamfieldaccess.models.TaskTime
import com.product.eamfieldaccess.models.WorkOrderExtension

class WorkOrderViewModel : ViewModel() {
    val currentTime: MutableLiveData<TaskTime> = MutableLiveData()
    val currentWorkOrderExtension: MutableLiveData<WorkOrderExtension> = MutableLiveData()
    val currentWorkTask: MutableLiveData<Int> = MutableLiveData()
}