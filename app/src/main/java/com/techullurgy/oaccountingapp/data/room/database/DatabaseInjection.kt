package com.techullurgy.oaccountingapp.data.room.database

import android.content.Context
import com.techullurgy.oaccountingapp.data.room.database.ExpensesTrackerDatabase

class DatabaseInjection private constructor() {
    companion object {
        operator fun invoke(context: Context): ExpensesTrackerDatabase {
            return ExpensesTrackerDatabase.getInstance(context)
        }
    }
}