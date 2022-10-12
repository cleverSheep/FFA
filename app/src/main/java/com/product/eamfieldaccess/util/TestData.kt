package com.product.eamfieldaccess.util

import com.product.eamfieldaccess.models.*

class TestData {
    companion object {
        val WORKTASKS = arrayListOf(
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
        val LABOR = arrayListOf(
            Labor(
                "3221",
                "laborEmployeeNameZero",
                "laborDateZero",
                100,
                100,
                "laborTimeZero",
                "laborSystemZero",
                "laborDescriptionZero"
            ),
            Labor(
                "3222",
                "laborEmployeeNameZero",
                "laborDateZero",
                100,
                100,
                "laborTimeZero",
                "laborSystemZero",
                "laborDescriptionZero"
            ),
            Labor(
                "3224",
                "laborEmployeeNameZero",
                "laborDateZero",
                100,
                100,
                "laborTimeZero",
                "laborSystemZero",
                "laborDescriptionZero"
            ),
            Labor(
                "3444",
                "laborEmployeeNameZero",
                "laborDateZero",
                100,
                100,
                "laborTimeZero",
                "laborSystemZero",
                "laborDescriptionZero"
            )
        )
        val CHECKLISTS = listOf(
            CheckList(
                "this item needs to be completed", listOf(
                    "here is some random text that describes the first item that describes the list",
                    "here is more random text that describes the first item that describes the list",
                    "here is even more random text that describes the first item that describes the list"
                )
            ),
            CheckList(
                "here's another item needs to be completed", listOf(
                    "here is some random text that describes the second item that describes the list",
                    "here is more random text that describes the second item that describes the list",
                    "here is even more random text that describes the second item that describes the list"
                )
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
                "here are some random notes here are more random notes here are even more random notes",
                "here is a standard task",
                WORKTASKS,
                LABOR,
                "1pm",
                "2pm",
                CHECKLISTS
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
                "here are some random notes here are more random notes here are even more random notes",
                "here is a standard task",
                WORKTASKS,
                LABOR,
                "1pm",
                "2pm",
                CHECKLISTS
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
                "here are some random notes here are more random notes here are even more random notes",
                "here is a standard task",
                WORKTASKS,
                LABOR,
                "1pm",
                "2pm",
                CHECKLISTS
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
                "here are some random notes here are more random notes here are even more random notes",
                "here is a standard task",
                WORKTASKS,
                LABOR,
                "1pm",
                "2pm",
                CHECKLISTS
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
                "here are some random notes here are more random notes here are even more random notes",
                "here is a standard task",
                WORKTASKS,
                LABOR,
                "1pm",
                "2pm",
                CHECKLISTS
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