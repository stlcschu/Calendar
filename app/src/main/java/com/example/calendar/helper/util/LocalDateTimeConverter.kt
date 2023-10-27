package com.example.calendar.helper.util

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.Locale

class LocalDateTimeConverter {

    fun convertToHourMinuteString(dateTime: LocalDateTime) : String {
        return "${dateTime.hour}:${dateTime.minute}"
    }

    fun convertToMonthDayString(dateTime: LocalDateTime) : String {
        return "${dateTime.dayOfMonth}. (${shortenWeekDayName(dateTime.dayOfWeek)}) ${convertMonthEnumStringToConventional(dateTime.month.toString())}"
    }

    fun shortenWeekDayName(weekDay: DayOfWeek) : String {
        return when(weekDay) {
            DayOfWeek.MONDAY -> "Mo"
            DayOfWeek.TUESDAY -> "Di"
            DayOfWeek.WEDNESDAY -> "Mi"
            DayOfWeek.THURSDAY -> "Do"
            DayOfWeek.FRIDAY -> "Fr"
            DayOfWeek.SATURDAY -> "Sa"
            DayOfWeek.SUNDAY -> "So"
        }
    }

    private fun convertMonthEnumStringToConventional(month: String) : String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(month[0].uppercaseChar())
        stringBuilder.append(month.substring(1, month.length).lowercase(Locale.ROOT))
        return stringBuilder.toString()
    }

}