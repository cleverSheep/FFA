package com.product.eamfieldaccess.util

import com.product.eamfieldaccess.models.*

class TestData {
    companion object {
        val WORKTASKS = listOf(
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
        val LABOR = listOf(
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
        val WORKORDERS = listOf(
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
                WORKTASKS,
                LABOR
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
                WORKTASKS,
                LABOR
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
                WORKTASKS,
                LABOR
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
                WORKTASKS,
                LABOR
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
                WORKTASKS,
                LABOR
            )
        )

        val SITES = listOf(
            Site("siteZero", "000", WORKORDERS),
            Site("siteTwo", "000", WORKORDERS),
            Site("siteThree", "000", WORKORDERS)
        )
        val EMPLOYEES = listOf(
            Employee("nameZero", "000", SITES),
            Employee("nameOne", "000", SITES),
            Employee("nameTwo", "000", SITES),
            Employee("nameThree", "000", SITES),
            Employee("nameFour", "000", SITES),
            Employee("nameFive", "000", SITES),
            Employee("nameSix", "000", SITES),
            Employee("nameSeven", "000", SITES),
            Employee("nameEight", "000", SITES)
        )
    }
}