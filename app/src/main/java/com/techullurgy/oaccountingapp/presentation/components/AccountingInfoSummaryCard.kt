package com.techullurgy.oaccountingapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techullurgy.oaccountingapp.domain.ApplicationUIState
import com.techullurgy.oaccountingapp.domain.ViewMode
import java.time.LocalDate

@Composable
fun AccountingInfoSummaryCard(
    applicationUIState: ApplicationUIState,
    modifier: Modifier = Modifier
) {
    AccountingInfoSummaryCard(
        modifier = modifier,
        overallIncome = applicationUIState.overallIncome,
        overallExpense = applicationUIState.overallExpense,
        viewMode = applicationUIState.viewMode,
        currentViewModeOverallIncome = applicationUIState.currentViewModeOverallIncome,
        currentViewModeOverallExpense = applicationUIState.currentViewModeOverallExpense
    )
}

@Composable
private fun AccountingInfoSummaryCard(
    overallIncome: String,
    overallExpense: String,
    viewMode: ViewMode,
    currentViewModeOverallIncome: String,
    currentViewModeOverallExpense: String,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 300.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Blue),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val rightTitlePrefix: String = when(viewMode) {
            is ViewMode.CustomDateRange -> "Custom Range"
            is ViewMode.LastNDays -> "Last ${viewMode.days} days"
            is ViewMode.MonthInfo -> "Last month"
        }

        AccountingInfoSummaryRow(
            leftTitle = "Overall Income",
            leftValue = overallIncome,
            rightTitle = "$rightTitlePrefix Income",
            rightValue = currentViewModeOverallIncome,
            leftValueColor = Color.White.copy(alpha = 0.8f),
            rightValueColor = Color.White.copy(alpha = 0.6f),
            modifier = Modifier.padding(20.dp),
        )
        AccountingInfoSummaryRow(
            leftTitle = "Overall Expense",
            leftValue = overallExpense,
            rightTitle = "$rightTitlePrefix Expense",
            rightValue = currentViewModeOverallExpense,
            leftValueColor = Color.White.copy(alpha = 0.8f),
            rightValueColor = Color.White.copy(alpha = 0.6f),
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Preview
@Composable
fun AccountingSummaryCardPreview() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White), contentAlignment = Alignment.Center) {
        AccountingInfoSummaryCard(
            overallIncome = "20000.00",
            overallExpense = "300",
            viewMode = ViewMode.MonthInfo(),
            currentViewModeOverallIncome = "30005.67",
            currentViewModeOverallExpense = "24958.9"
        )
    }
}