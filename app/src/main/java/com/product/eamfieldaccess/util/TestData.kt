package com.product.eamfieldaccess.util

import com.product.eamfieldaccess.models.*

class TestData {
    companion object {
        val ALL_WORK_WORK_TASKS = arrayListOf(
            WorkTask(
                "",
                "workTaskCategoryZero",
                "workTaskCodeThree",
                "workTasDescriptionZero",
                "workTaskNotesZero"
            ),
            WorkTask(
                "",
                "workTaskCategoryZero",
                "workTaskCodeZero",
                "workTasDescriptionZero",
                "workTaskNotesZero"
            ),
            WorkTask(
                "",
                "workTaskCategoryOne",
                "workTaskCodeOne",
                "workTasDescriptionZero",
                "workTaskNotesZero"
            )
        )
        val WORKTASKS = arrayListOf(
            WorkTask(
                "111",
                "workTaskCategoryZero",
                "workTaskCodeZero",
                "workTasDescriptionZero",
                "workTaskNotesZero"
            )
        )
        val WORKTASKS_TWO = arrayListOf(
            WorkTask(
                "222",
                "workTaskCategoryOne",
                "workTaskCodeOne",
                "workTasDescriptionZero",
                "workTaskNotesZero"
            )
        )

        val LABOR = arrayListOf(
            Labor(
                "111",
                "3221",
                "workTaskCodeZero",
                "laborEmployeeNameZero",
                "laborDateZero",
                "100",
                "100",
                "200",
                "laborSystemZero",
                "laborDescriptionZero"
            )
        )
        val LABOR_TWO = arrayListOf(
            Labor(
                "222",
                "3221",
                "workTaskCodeOne",
                "laborEmployeeNameZero",
                "laborDateZero",
                "100",
                "100",
                "200",
                "laborSystemZero",
                "laborDescriptionZero"
            )
        )

        val CHECKLISTS = arrayListOf(
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
                "111",
                "workOrderSiteZero",
                "workOrderLocationZero",
                "workOrderUnitZero",
                "workOrderZero",
                "workOrderStatusZero",
                "workOrderActivityZero",
                "workOrderMaintenanceZero",
                "workOrderStandardTaskZero",
                "liltas",
                "here are some random notes here are more random notes here are even more random notes",
                "here is a standard task",
                WORKTASKS,
                LABOR,
                "1pm",
                "2pm",
                CHECKLISTS
            )
        )
        val WORKORDERS_TWO = listOf(
            WorkOrder(
                "222",
                "workOrderSiteOne",
                "workOrderLocationZero",
                "workOrderUnitZero",
                "workOrderOne",
                "workOrderStatusZero",
                "workOrderActivityZero",
                "workOrderMaintenanceZero",
                "workOrderStandardTaskZero",
                "liltas",
                "here are some random notes here are more random notes here are even more random notes",
                "here is a standard task",
                WORKTASKS_TWO,
                LABOR_TWO,
                "1pm",
                "2pm",
                CHECKLISTS
            )
        )


        val SITES = listOf(
            Site("siteZero", "000", WORKORDERS)
            //Site("siteTwo", "000", WORKORDERS_TWO)
            //Site("siteThree", "000", WORKORDERS)
        )
        val SITES_TWO = listOf(
            //Site("siteZero", "000", WORKORDERS),
            Site("siteTwo", "000", WORKORDERS_TWO)
            //Site("siteThree", "000", WORKORDERS)
        )
        val EMPLOYEES = listOf(
            Employee("nameZero", "000", SITES),
            Employee("nameOne", "111", SITES_TWO)
            // Employee("nameTwo", "000", SITES)
/*          Employee("nameThree", "000", SITES),
            Employee("nameFour", "000", SITES),
            Employee("nameFive", "000", SITES),
            Employee("nameSix", "000", SITES),
            Employee("nameSeven", "000", SITES),
            Employee("nameEight", "000", SITES)*/
        )
    }
}