package com.product.eamfieldaccess.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.product.eamfieldaccess.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = arrayOf(
        EmployeeEntity::class,
        WorkOrderEntity::class,
        WorkTaskEntity::class,
        WorkLaborEntity::class,
        CheckListEntity::class,
        CheckListItemEntity::class
    ), version = 2, exportSchema = false
)
abstract class EmployeeRoomDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    private class EmployeeDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var employeeDao = database.employeeDao()
                    var employee = EmployeeEntity("Jon Robers", "0001")
                    var employeeTwo = EmployeeEntity("Sarah Shells", "0002")
                    employeeDao.insert(employee)
                    employeeDao.insert(employeeTwo)
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: EmployeeRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): EmployeeRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeRoomDatabase::class.java,
                    "employee_database"
                )
                    .addCallback(EmployeeDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}