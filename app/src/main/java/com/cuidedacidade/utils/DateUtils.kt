package com.cuidedacidade.utils

import java.util.*

object DateUtils {
    fun isToday(dateToCompare: Date): Boolean {
        val today =
            DateCalendar(Date())
        val date = DateCalendar(
            dateToCompare
        )

        return (today.dayOfMonth == date.dayOfMonth && today.month == date.month && today.year == date.year)
    }

    fun isYesterday(dateToCompare: Date): Boolean {
        val yesterday = DateCalendar(
            Date()
        ).also {
            it.calendar.add(Calendar.DAY_OF_MONTH, -1)
        }
        val date = DateCalendar(
            dateToCompare
        )

        return (yesterday.dayOfMonth == date.dayOfMonth && yesterday.month == date.month && yesterday.year == date.year)
    }

    class DateCalendar(
        private val date: Date
    ) {
        val calendar: Calendar by lazy { Calendar.getInstance().also { it.time = date } }
        val dayOfMonth: Int by lazy { calendar.get(Calendar.DAY_OF_MONTH) }
        val month: Int by lazy { calendar.get(Calendar.MONTH) }
        val year: Int by lazy { calendar.get(Calendar.YEAR) }
    }
}