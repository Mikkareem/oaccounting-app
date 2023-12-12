package com.techullurgy.oaccountingapp.presentation

import com.techullurgy.oaccountingapp.domain.AccountingInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface OAccountingViewModelInterface {
    fun onAddAccountingInfo(accountingInfo: AccountingInfo)
    fun onDeleteAccountingInfo(accountingInfo: AccountingInfo)
    fun getLastNDaysReport(days: Int)
    fun getLastMonthReport()
    fun getCustomRangeReport(startDate: LocalDate, endDate: LocalDate)
}