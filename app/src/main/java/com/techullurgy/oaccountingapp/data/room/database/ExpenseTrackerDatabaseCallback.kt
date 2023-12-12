package com.techullurgy.oaccountingapp.data.room.database

import android.content.ContentValues
import androidx.room.OnConflictStrategy
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.techullurgy.oaccountingapp.data.room.database.fake.fakeAccountingInfoEntities

class ExpenseTrackerDatabaseCallback: RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val contentValues = ContentValues().apply {
            fakeAccountingInfoEntities.forEach {
                put("amount", it.amount)
                put("desc", it.desc)
                put("infoType", it.infoType.name)
                put("timestamp", it.timestamp)
            }
        }

        db.use {
            it.insert("AccountingInfoEntity", OnConflictStrategy.REPLACE, contentValues)
        }
    }
}