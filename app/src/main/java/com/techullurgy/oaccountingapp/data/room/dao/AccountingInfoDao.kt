package com.techullurgy.oaccountingapp.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techullurgy.oaccountingapp.data.room.entities.AccountingInfoEntity
import com.techullurgy.oaccountingapp.domain.AccountingInfo
import com.techullurgy.oaccountingapp.domain.AmountDetail
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AccountingInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAccountingInfo(accountingInfo: AccountingInfoEntity)

    @Delete
    abstract suspend fun deleteAccountingInfo(accountingInfo: AccountingInfoEntity)

    @Query("select * from AccountingInfoEntity where timestamp >= :startTimeMillis and timestamp <= :endTimeMillis")
    abstract fun getCustomRangeDataAccountingInfo(startTimeMillis: Long, endTimeMillis: Long): Flow<List<AccountingInfoEntity>>

    @Query("select infoType, amount from AccountingInfoEntity")
    abstract fun getAllAmounts(): Flow<List<AmountDetail>>
}