package com.techullurgy.oaccountingapp.utils

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId


fun LocalDate.toEpochMilliSeconds(): Long
    = this.atStartOfDay().toInstant(ZoneId.systemDefault().rules.getOffset(Instant.now())).toEpochMilli()

fun LocalDateTime.toEpochMilliSeconds(): Long
    = this.toInstant(ZoneId.systemDefault().rules.getOffset(Instant.now())).toEpochMilli()

fun Long.toLocalDate(): LocalDate
    = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()

fun LocalDate.atEndOfDay(): LocalDateTime = this.plusDays(1).atStartOfDay().minusSeconds(2)

fun LocalDate.toDateString(): String
    = if(this.isEqual(LocalDate.now())) "Today" else if(this.isEqual(LocalDate.now().minusDays(1))) "Yesterday" else this.toString()

fun getLastMonthFirstDate(): LocalDate = LocalDate.now().minusDays(LocalDate.now().dayOfMonth-1L).minusMonths(1)

fun getLastMonthLastDate(): LocalDate = LocalDate.now().minusDays((LocalDate.now().dayOfMonth).toLong())

fun getCurrentMonthFirstDate(): LocalDate = LocalDate.now().minusDays(LocalDate.now().dayOfMonth-1L)

fun getSelectedMonthFirstDay(year: Int, month: Month): DayOfWeek = LocalDate.of(year, month, 1).dayOfWeek