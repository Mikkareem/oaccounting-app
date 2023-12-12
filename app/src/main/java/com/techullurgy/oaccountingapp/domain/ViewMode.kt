package com.techullurgy.oaccountingapp.domain

import java.time.LocalDate
import java.time.Month

sealed class ViewMode {
    data class LastNDays(val days: Int = 30): ViewMode()
    data class MonthInfo(val month: Month = LocalDate.now().month): ViewMode()
    data class CustomDateRange(val startDate: LocalDate, val endDate: LocalDate): ViewMode()
}