package com.techullurgy.oaccountingapp.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.techullurgy.oaccountingapp.data.room.dao.AccountingInfoDao
import com.techullurgy.oaccountingapp.data.room.entities.AccountingInfoEntity

@Database(
    version = 1,
    entities = [AccountingInfoEntity::class]
)
abstract class ExpensesTrackerDatabase: RoomDatabase() {
    abstract fun accountingInfoDao(): AccountingInfoDao

    companion object {
        @Volatile
        private var INSTANCE: ExpensesTrackerDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ExpensesTrackerDatabase {
            return INSTANCE ?: run {
                    val database = Room.databaseBuilder(
                        context,
                        ExpensesTrackerDatabase::class.java,
                        "expense_tracker"
                    )
                        .addCallback(ExpenseTrackerDatabaseCallback())
                        .build()
                    INSTANCE = database
                INSTANCE!!
            }
        }
    }
}