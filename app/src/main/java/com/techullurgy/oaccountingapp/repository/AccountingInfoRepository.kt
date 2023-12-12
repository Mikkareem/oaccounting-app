package com.techullurgy.oaccountingapp.repository

import android.content.Context
import com.techullurgy.oaccountingapp.data.room.dao.AccountingInfoDao
import com.techullurgy.oaccountingapp.data.room.database.DatabaseInjection
import com.techullurgy.oaccountingapp.domain.AccountingInfo
import com.techullurgy.oaccountingapp.domain.AmountDetail
import com.techullurgy.oaccountingapp.domain.mappers.toAccountingInfo
import com.techullurgy.oaccountingapp.domain.mappers.toAccountingInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountingInfoRepository(
    context: Context,
    private val accountingInfoDao: AccountingInfoDao = DatabaseInjection(context).accountingInfoDao()
) {
    suspend fun onAddAccountingInfo(accountingInfo: AccountingInfo) {
        accountingInfoDao.insertAccountingInfo(accountingInfo.toAccountingInfoEntity())
    }

    suspend fun onDeleteAccountingInfo(accountingInfo: AccountingInfo) {
        accountingInfoDao.deleteAccountingInfo(accountingInfo.toAccountingInfoEntity())
    }

    fun getAllAmounts(): Flow<List<AmountDetail>> = accountingInfoDao.getAllAmounts()

    fun getCustomDateRangesAccountingInfo(startTimeMillis: Long, endTimeMillis: Long): Flow<List<AccountingInfo>>
        = accountingInfoDao.getCustomRangeDataAccountingInfo(startTimeMillis, endTimeMillis).map {
            it.map { accountingInfoEntity ->
                accountingInfoEntity.toAccountingInfo()
            }
        }
}