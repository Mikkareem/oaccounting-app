package com.techullurgy.oaccountingapp.domain

import com.techullurgy.oaccountingapp.data.room.database.fake.fakeAccountingInfoEntities
import com.techullurgy.oaccountingapp.domain.mappers.toAccountingInfo

data class ApplicationUIState(
    val accountingInfoList: List<AccountingInfo>,
    val viewMode: ViewMode,
    val overallIncome: String = "0.00",
    val overallExpense: String = "0.00",
    val currentViewModeOverallIncome: String = "0.00",
    val currentViewModeOverallExpense: String = "0.00"
)

internal val FakeApplicationUIState: ApplicationUIState
    get() =
        ApplicationUIState(
            accountingInfoList = fakeAccountingInfoEntities.map { it.toAccountingInfo() },
            viewMode = ViewMode.LastNDays(45),
            overallExpense = "12,934.00",
            overallIncome = "23,845.50",
            currentViewModeOverallExpense = "1122.43",
            currentViewModeOverallIncome = "123.44"
        )
