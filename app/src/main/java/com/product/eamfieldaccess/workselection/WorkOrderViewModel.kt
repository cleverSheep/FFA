package com.product.eamfieldaccess.workselection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.product.eamfieldaccess.models.TaskTime
import com.product.eamfieldaccess.models.WorkOrder
import com.product.eamfieldaccess.workpanel.tabs.worktasks.WorkTaskAdapter

class WorkOrderViewModel : ViewModel() {
    val currentTime: MutableLiveData<TaskTime> = MutableLiveData()
    val currentWorkOrder: MutableLiveData<WorkOrder> = MutableLiveData()
    val currentWorkTask: MutableLiveData<Int> = MutableLiveData()
}