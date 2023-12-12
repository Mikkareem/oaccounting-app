package com.techullurgy.oaccountingapp.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.techullurgy.oaccountingapp.domain.AccountingInfo

@Composable
fun AccountingInfoListView(
    accountingInfoList: List<AccountingInfo>
) {
    LazyColumn {
        items(accountingInfoList) {
            AccountingInfoItem(accountingInfo = it)
        }
    }
}