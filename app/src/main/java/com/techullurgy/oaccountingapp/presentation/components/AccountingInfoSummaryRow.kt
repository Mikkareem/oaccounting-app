package com.techullurgy.oaccountingapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun AccountingInfoSummaryRow(
    leftTitle: String,
    leftValue: String,
    rightTitle: String,
    rightValue: String,
    leftValueColor: Color,
    rightValueColor: Color,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        AccountingInfoSummaryItem(
            title = leftTitle,
            value = leftValue,
            valueColor = leftValueColor,
            alignment = Alignment.Start,
            isElevated = true,
        )
        AccountingInfoSummaryItem(
            title = rightTitle,
            value = rightValue,
            valueColor = rightValueColor,
            alignment = Alignment.End,
            useTextMeasure = true
        )
    }
}