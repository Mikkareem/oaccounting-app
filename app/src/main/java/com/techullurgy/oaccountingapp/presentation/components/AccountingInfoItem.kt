package com.techullurgy.oaccountingapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techullurgy.oaccountingapp.data.room.database.fake.fakeAccountingInfoEntities
import com.techullurgy.oaccountingapp.domain.AccountingInfo
import com.techullurgy.oaccountingapp.domain.AccountingInfoType
import com.techullurgy.oaccountingapp.domain.mappers.toAccountingInfo
import com.techullurgy.oaccountingapp.utils.toDateString

@Composable
fun AccountingInfoItem(
    accountingInfo: AccountingInfo,
    modifier: Modifier = Modifier
) {
    val amountStr: String = when(accountingInfo.infoType) {
        AccountingInfoType.INCOME -> "+${accountingInfo.amount}"
        AccountingInfoType.EXPENSE -> "-${accountingInfo.amount}"
    }

    val amountStrColor: Color = when(accountingInfo.infoType) {
        AccountingInfoType.INCOME -> Color.Green
        AccountingInfoType.EXPENSE -> Color.Red
    }

    val timeStr: String = accountingInfo.date.toDateString()

    AccountingInfoItem(
        modifier = modifier,
        description = accountingInfo.desc,
        amountStr = amountStr,
        amountStrColor = amountStrColor,
        timeStr = timeStr
    )
}

@Composable
private fun AccountingInfoItem(
    modifier: Modifier = Modifier,
    description: String,
    amountStr: String,
    amountStrColor: Color,
    timeStr: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            AppText(text = description)
            AppText(text = timeStr, fontColor = Color.Gray, fontSize = 16.sp)
        }
        AppText(text = amountStr, fontColor = amountStrColor)
    }
}

@Preview
@Composable
fun AccountingInfoItemPreview() {
    AccountingInfoItem(accountingInfo = fakeAccountingInfoEntities[1].toAccountingInfo())
}