package com.techullurgy.oaccountingapp.data.room.database.fake

import android.content.Context
import com.techullurgy.oaccountingapp.data.room.database.DatabaseInjection

class FakeDatabaseCreation private constructor() {
    companion object {
        suspend operator fun invoke(context: Context): Unit {
            fakeAccountingInfoEntities.forEach {
                DatabaseInjection(context).accountingInfoDao().insertAccountingInfo(it)
            }
        }
    }
}