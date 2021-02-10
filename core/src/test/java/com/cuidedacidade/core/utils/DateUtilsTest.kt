package com.cuidedacidade.core.utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

class DateUtilsTest {
    @Test
    fun should_ReturnTrue_When_IsToday() {
        val today = Date()

        val isToday = DateUtils.isToday(today)

        assertTrue(isToday)
    }

    @Test
    fun should_ReturnFalse_When_IsNotToday() {
        val tomorrow = addDaysToDate(Date(), 1)
        val yesterday = addDaysToDate(Date(), -1)

        var isToday = DateUtils.isToday(tomorrow)
        assertFalse(isToday)

        isToday = DateUtils.isToday(yesterday)
        assertFalse(isToday)
    }

    @Test
    fun should_ReturnTrue_When_IsYesterday() {
        val yesterday = addDaysToDate(Date(), -1)

        val isYesterday = DateUtils.isYesterday(yesterday)

        assertTrue(isYesterday)
    }

    @Test
    fun should_ReturnFalse_When_IsNotYesterday() {
        val tomorrow = addDaysToDate(Date(), 1)
        val today = Date()

        var isYesterday = DateUtils.isYesterday(today)
        assertFalse(isYesterday)

        isYesterday = DateUtils.isYesterday(tomorrow)
        assertFalse(isYesterday)
    }

    private fun addDaysToDate(date: Date, days: Int): Date {
        return DateUtils.DateCalendar(date).apply {
            calendar.add(Calendar.DAY_OF_MONTH, days)
        }.calendar.time
    }
}