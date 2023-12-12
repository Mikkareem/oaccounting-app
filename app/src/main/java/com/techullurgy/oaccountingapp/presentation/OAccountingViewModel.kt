package com.techullurgy.oaccountingapp.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techullurgy.oaccountingapp.data.room.database.fake.FakeDatabaseCreation
import com.techullurgy.oaccountingapp.domain.AccountingInfo
import com.techullurgy.oaccountingapp.domain.AccountingInfoType
import com.techullurgy.oaccountingapp.domain.ApplicationUIState
import com.techullurgy.oaccountingapp.domain.ViewMode
import com.techullurgy.oaccountingapp.repository.AccountingInfoRepository
import com.techullurgy.oaccountingapp.utils.atEndOfDay
import com.techullurgy.oaccountingapp.utils.getLastMonthFirstDate
import com.techullurgy.oaccountingapp.utils.getLastMonthLastDate
import com.techullurgy.oaccountingapp.utils.toEpochMilliSeconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate


@OptIn(ExperimentalCoroutinesApi::class)
class OAccountingViewModel(
    context: Context
) : ViewModel(), OAccountingViewModelInterface {

    private val accountingInfoRepository: AccountingInfoRepository = AccountingInfoRepository(context)

    private val viewModeFlow = MutableStateFlow<ViewMode>(ViewMode.LastNDays())

    private val _applicationStateFlow: MutableStateFlow<ApplicationUIState> = MutableStateFlow(
        ApplicationUIState(emptyList(), viewModeFlow.value)
    )
    val applicationStateFlow: StateFlow<ApplicationUIState>
        get() = _applicationStateFlow.asStateFlow()

    init {

        viewModeFlow
            .onEach { _applicationStateFlow.value = _applicationStateFlow.value.copy(viewMode = it) }
            .flatMapLatest { viewMode ->
                when (viewMode) {
                    is ViewMode.CustomDateRange -> {
                        accountingInfoRepository.getCustomDateRangesAccountingInfo(
                            startTimeMillis = viewMode.startDate.toEpochMilliSeconds(),
                            endTimeMillis = viewMode.endDate.atEndOfDay().toEpochMilliSeconds()
                        )
                    }

                    is ViewMode.LastNDays -> {
                        accountingInfoRepository.getCustomDateRangesAccountingInfo(
                            startTimeMillis = LocalDate.now().minusDays(viewMode.days.toLong())
                                .toEpochMilliSeconds(),
                            endTimeMillis = LocalDate.now().atEndOfDay().toEpochMilliSeconds()
                        )
                    }

                    is ViewMode.MonthInfo -> {
                        accountingInfoRepository.getCustomDateRangesAccountingInfo(
                            startTimeMillis = getLastMonthFirstDate().toEpochMilliSeconds(),
                            endTimeMillis = getLastMonthLastDate().atEndOfDay().toEpochMilliSeconds()
                        )
                    }
                }
            }
            .combine(accountingInfoRepository.getAllAmounts()) { accountingInfo, amounts ->
                val overallIncomes = amounts.filter { it.infoType == AccountingInfoType.INCOME }.map { it.amount }.sum()
                val overallExpense = amounts.filter { it.infoType == AccountingInfoType.EXPENSE }.map { it.amount }.sum()
                val overallPair = Pair(overallIncomes, overallExpense)
                Pair(accountingInfo, overallPair)
            }
            .map {
                Pair(it.first.toMutableList().sortedDescending(), it.second)
            }
            .onEach { pairs ->
                _applicationStateFlow.value = _applicationStateFlow.value.copy(
                    accountingInfoList = pairs.first,
                    overallIncome = String.format("%.2f", pairs.second.first),
                    overallExpense = String.format("%.2f", pairs.second.second),
                    currentViewModeOverallIncome = String.format("%.2f", pairs.first.map { Pair(it.amount, it.infoType) }.filter { it.second == AccountingInfoType.INCOME }.map { it.first }.sum()),
                    currentViewModeOverallExpense = String.format("%.2f", pairs.first.map { Pair(it.amount, it.infoType) }.filter { it.second == AccountingInfoType.EXPENSE }.map { it.first }.sum()),
                )
            }.launchIn(viewModelScope)
    }

    override fun onAddAccountingInfo(accountingInfo: AccountingInfo) {
        viewModelScope.launch {
            accountingInfoRepository.onAddAccountingInfo(accountingInfo)
        }
    }

    override fun onDeleteAccountingInfo(accountingInfo: AccountingInfo) {
        viewModelScope.launch {
            accountingInfoRepository.onDeleteAccountingInfo(accountingInfo)
        }
    }

    override fun getLastNDaysReport(days: Int) {
        viewModeFlow.value = ViewMode.LastNDays(days)
    }

    override fun getLastMonthReport() {
        viewModeFlow.value = ViewMode.MonthInfo()
    }

    override fun getCustomRangeReport(startDate: LocalDate, endDate: LocalDate) {
        viewModeFlow.value = ViewMode.CustomDateRange(startDate, endDate)
    }
}