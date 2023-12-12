package com.techullurgy.oaccountingapp.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.techullurgy.oaccountingapp.domain.AccountingInfoType


@Entity
data class AccountingInfoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val desc: String,
    val amount: Float,
    val timestamp: Long,
    val infoType: AccountingInfoType
)

