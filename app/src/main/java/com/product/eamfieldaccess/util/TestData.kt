package com.product.eamfieldaccess.util

class TestData {
    companion object {
        /**
         * All of the available works tasks. These are the only work tasks we can add to a work order.
         * The work order id is empty by default, but will be set when it's added to a work order.
         */
/*        val ALL_WORK_WORK_TASKS = arrayListOf(
            WorkTaskExtension(
                workOrderId = "",
                employeeId = "",
                category = "workTaskCategoryZero",
                code = "workTaskCodeThree",
                description = "workTasDescriptionZero",
                notes = "workTaskNotesZero"
            ),
            WorkTaskExtension(
                workOrderId = "",
                employeeId = "",
                category = "workTaskCategoryZero",
                code = "workTaskCodeZero",
                description = "workTasDescriptionZero",
                notes = "workTaskNotesZero"
            ),
            WorkTaskExtension(
                workOrderId = "",
                employeeId = "",
                category = "workTaskCategoryOne",
                code = "workTaskCodeOne",
                description = "workTasDescriptionZero",
                notes = "workTaskNotesZero"
            )*/
        //)

        /**
         * Work tasks that are tied to a single work order. The work task code is unique among all work tasks.
         */
/*        val WORKTASKS = arrayListOf(
            WorkTaskExtension(
                workOrderId = "111",
                category = "workTaskCategoryZero",
                employeeId = "3220",
                code = "workTaskCodeZero",
                description = "workTasDescriptionZero",
                notes = "workTaskNotesZero"
            )
        )
        val WORKTASKS_TWO = arrayListOf(
            WorkTaskExtension(
                workOrderId = "222",
                category = "workTaskCategoryOne",
                employeeId = "3221",
                code = "workTaskCodeOne",
                description = "workTasDescriptionZero",
                notes = "workTaskNotesZero"
            )*/
        //)

        /**
         * This item defines which employee is assigned to a single task. Any updates to work time should be reflected in this date.
         * It's important that the updates to work task time target the correct labor. For that reason, this class requires a work order id
         * AND work task code. This requirement will ensure that only tasks with the given work order id and task code update its work task time.
         */
/*        val LABOR = arrayListOf(
            Labor(
                workOrderId = "111",
                employeeId = "3220",
                workTaskCode = "workTaskCodeZero",
                date = "laborDateZero",
                startTime = "100",
                endTime = "100",
                totalTime = "200",
                system = "laborSystemZero",
                description = "laborDescriptionZero"
            )
        )
        val LABOR_TWO = arrayListOf(
            Labor(
                "222",
                "3221",
                "workTaskCodeOne",
                "laborEmployeeNameZero",
                false,
                0, 0,
                "laborDateZero",
                "100",
                "100",
                "200",
                "laborSystemZero",
                "laborDescriptionZero"
            )
        )*/

        /**
         * In some cases work orders have, for the most part, random tasks that need to be checked off. These tasks are separate from the work
         * tasks mentioned above. Completing the tasks is not required.
         */
/*        val CHECKLISTS = arrayListOf(
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
                "3220",
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
                "3221",
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
        )*/


/*        val SITES = listOf(
            Site("siteZero", "000", WORKORDERS)
        )
        val SITES_TWO = listOf(
            Site("siteTwo", "000", WORKORDERS_TWO)
        )
        val AUTHENTICATED_EMPLOYEE = Employee("authenticated_employee", "3221", SITES_TWO)
        val EMPLOYEES = listOf(
            AUTHENTICATED_EMPLOYEE,
            Employee("nameZero", "3220", SITES),
        )*/
    }
}