package com.techullurgy.oaccountingapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun AccountingInfoSummaryItem(
    title: String,
    value: String,
    valueColor: Color,
    alignment: Alignment.Horizontal,
    modifier: Modifier = Modifier,
    isElevated: Boolean = false,
    useTextMeasure: Boolean = false
) {

    Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = alignment) {
        AppText(
            text = title,
            textAlign = if(alignment == Alignment.Start) TextAlign.Start else TextAlign.End,
            fontSize = if(isElevated) 24.sp else 18.sp,
            fontColor = Color.White,
            useTextMeasure = useTextMeasure,
            measureText = { title.split(" ").take(3).joinToString(" ") }
        )
        AppText(
            text = value,
            fontSize = if(isElevated) 20.sp else 14.sp,
            fontColor = valueColor
        )
    }
}
