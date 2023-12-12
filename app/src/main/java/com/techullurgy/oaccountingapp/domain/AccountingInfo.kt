package com.techullurgy.oaccountingapp.domain

import androidx.compose.runtime.Stable
import java.time.LocalDate

@Stable
data class AccountingInfo (
    val id: Int = 0,
    val desc: String,
    val amount: Float,
    val date: LocalDate,
    val infoType: AccountingInfoType
): Comparable<AccountingInfo> {
    override fun compareTo(other: AccountingInfo): Int {
        return this.date.compareTo(other.date)
    }
}

