package com.techullurgy.oaccountingapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.techullurgy.oaccountingapp.domain.ApplicationUIState
import com.techullurgy.oaccountingapp.domain.FakeApplicationUIState
import com.techullurgy.oaccountingapp.presentation.OAccountingViewModel
import com.techullurgy.oaccountingapp.presentation.components.AccountingInfoListView
import com.techullurgy.oaccountingapp.presentation.components.AccountingInfoSummaryCard
import com.techullurgy.oaccountingapp.presentation.components.AccountingInfoTabView

@Composable
fun MainScreen(
    viewModel: OAccountingViewModel,
    modifier: Modifier = Modifier
) {
    val applicationState = viewModel.applicationStateFlow.collectAsState().value

    MainScreen(
        applicationState = applicationState,
        modifier = modifier,
        onLastNDaysClick = { viewModel.getLastNDaysReport(it) },
        onGetLastMonthReportClick = viewModel::getLastMonthReport,
        onCustomDateRangeReportClick = viewModel::getLastMonthReport
    )
}

@Composable
private fun MainScreen(
    applicationState: ApplicationUIState,
    modifier: Modifier = Modifier,
    onLastNDaysClick: (Int) -> Unit = {},
    onGetLastMonthReportClick: () -> Unit = {},
    onCustomDateRangeReportClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        AccountingInfoSummaryCard(
            applicationUIState = applicationState
        )
        AccountingInfoTabView(
            onFirstTabClick = { onLastNDaysClick(it) },
            onSecondTabClick = onGetLastMonthReportClick,
            onThirdTabClick = onCustomDateRangeReportClick
        )
        AccountingInfoListView(
            accountingInfoList = applicationState.accountingInfoList
        )
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(applicationState = FakeApplicationUIState)
}