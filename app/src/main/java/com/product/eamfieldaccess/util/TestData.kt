package com.product.eamfieldaccess.util

import com.product.eamfieldaccess.models.*

class TestData {
    companion object {
        val EMPLOYEES = listOf(
            Employee("nameZero", "000", TestData.SITES),
            Employee("nameOne", "000", TestData.SITES),
            Employee("nameTwo", "000", TestData.SITES),
            Employee("nameThree", "000", TestData.SITES),
            Employee("nameFour", "000", TestData.SITES),
            Employee("nameFive", "000", TestData.SITES),
            Employee("nameSix", "000", TestData.SITES),
            Employee("nameSeven", "000", TestData.SITES),
            Employee("nameEight", "000", TestData.SITES)
        )
        private val SITES = listOf(
            Site("siteZero", "000", TestData.WORKORDERS),
            Site("siteTwo", "000", TestData.WORKORDERS),
            Site("siteThree", "000", TestData.WORKORDERS)
        )
        private val WORKORDERS = listOf(
            WorkOrder(
                "workOrderSiteZero",
                "workOrderLocationZero",
                "workOrderUnitZero",
                "workOrderZero",
                "workOrderStatusZero",
                "workOrderActivityZero",
                "workOrderMaintenanceZero",
                "workOrderStandardTaskZero",
                "workOrderStandardTaskZero",
                TestData.WORKTASKS,
                TestData.LABOR
            ),
            WorkOrder(
                "workOrderSiteZero",
                "workOrderLocationZero",
                "workOrderUnitZero",
                "workOrderZero",
                "workOrderStatusZero",
                "workOrderActivityZero",
                "workOrderMaintenanceZero",
                "workOrderStandardTaskZero",
                "workOrderStandardTaskZero",
                TestData.WORKTASKS,
                TestData.LABOR
            ),
            WorkOrder(
                "workOrderSiteZero",
                "workOrderLocationZero",
                "workOrderUnitZero",
                "workOrderZero",
                "workOrderStatusZero",
                "workOrderActivityZero",
                "workOrderMaintenanceZero",
                "workOrderStandardTaskZero",
                "workOrderStandardTaskZero",
                TestData.WORKTASKS,
                TestData.LABOR
            ),
            WorkOrder(
                "workOrderSiteZero",
                "workOrderLocationZero",
                "workOrderUnitZero",
                "workOrderZero",
                "workOrderStatusZero",
                "workOrderActivityZero",
                "workOrderMaintenanceZero",
                "workOrderStandardTaskZero",
                "workOrderStandardTaskZero",
                TestData.WORKTASKS,
                TestData.LABOR
            ),
            WorkOrder(
                "workOrderSiteZero",
                "workOrderLocationZero",
                "workOrderUnitZero",
                "workOrderZero",
                "workOrderStatusZero",
                "workOrderActivityZero",
                "workOrderMaintenanceZero",
                "workOrderStandardTaskZero",
                "workOrderStandardTaskZero",
                TestData.WORKTASKS,
                TestData.LABOR
            )
        )
        private val WORKTASKS = listOf(
            WorkTask(
                "workTaskCategoryZero",
                "workTaskCodeZero",
                "workTasDescriptionZero",
                "workTaskNotesZero"
            ),
            WorkTask(
                "workTaskCategoryZero",
                "workTaskCodeZero",
                "workTasDescriptionZero",
                "workTaskNotesZero"
            ),
            WorkTask(
                "workTaskCategoryZero",
                "workTaskCodeZero",
                "workTasDescriptionZero",
                "workTaskNotesZero"
            ),
            WorkTask(
                "workTaskCategoryZero",
                "workTaskCodeZero",
                "workTasDescriptionZero",
                "workTaskNotesZero"
            )
        )
        private val LABOR = listOf(
            Labor(
                "laborEmployeeNameZero",
                "laborDateZero",
                100,
                100,
                "laborTimeZero",
                "laborSystemZero",
                "laborDescriptionZero"
            )
        )
    }
}