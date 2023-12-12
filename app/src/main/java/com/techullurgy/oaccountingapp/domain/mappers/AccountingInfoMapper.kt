package com.techullurgy.oaccountingapp.domain.mappers

import com.techullurgy.oaccountingapp.data.room.entities.AccountingInfoEntity
import com.techullurgy.oaccountingapp.domain.AccountingInfo
import com.techullurgy.oaccountingapp.utils.toEpochMilliSeconds
import com.techullurgy.oaccountingapp.utils.toLocalDate

fun AccountingInfo.toAccountingInfoEntity(): AccountingInfoEntity = AccountingInfoEntity(
    id = id,
    desc = desc,
    amount = amount,
    timestamp = date.toEpochMilliSeconds(),
    infoType = infoType
)


fun AccountingInfoEntity.toAccountingInfo(): AccountingInfo = AccountingInfo(
    id = id,
    desc = desc,
    amount = amount,
    date = timestamp.toLocalDate(),
    infoType = infoType
)