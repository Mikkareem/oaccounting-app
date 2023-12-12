package com.techullurgy.oaccountingapp.presentation.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AccountingInfoTabView(
    modifier: Modifier = Modifier,
    onFirstTabClick: (Int) -> Unit = {},
    onSecondTabClick: () -> Unit = {},
    onThirdTabClick: () -> Unit = {}
) {
    Row(modifier = modifier.height(IntrinsicSize.Max)) {
        Button(
            onClick = { onFirstTabClick(30) },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(0)
        ) { AppText(text = "Last 30 days", textAlign = TextAlign.Center) }

        VerticalDivider()

        Button(
            onClick = onSecondTabClick,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(0)
        ) { AppText(text = "Last Month", textAlign = TextAlign.Center) }

        VerticalDivider()

        Button(
            onClick = onThirdTabClick,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(0)
        ) { AppText(text = "Custom Range", textAlign = TextAlign.Center) }
    }
}