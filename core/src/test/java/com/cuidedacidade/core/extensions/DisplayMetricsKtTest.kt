package com.cuidedacidade.core.extensions

import android.util.DisplayMetrics
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DisplayMetricsKtTest {

    private val displayMetrics = DisplayMetrics()

    @Test
    fun should_ReturnLDPI_When_DensityLow() {
        displayMetrics.densityDpi = DisplayMetrics.DENSITY_LOW

        val densityQualifier = displayMetrics.getDensityQualifier()

        assertEquals("ldpi", densityQualifier)
    }

    @Test
    fun should_ReturnMDPI_When_DensityMedium() {
        displayMetrics.densityDpi = DisplayMetrics.DENSITY_MEDIUM

        val densityQualifier = displayMetrics.getDensityQualifier()

        assertEquals("mdpi", densityQualifier)
    }

    @Test
    fun should_ReturnHDPI_When_DensityHigh() {
        displayMetrics.densityDpi = DisplayMetrics.DENSITY_HIGH

        val densityQualifier = displayMetrics.getDensityQualifier()

        assertEquals("hdpi", densityQualifier)
    }

    @Test
    fun should_ReturnXHIGH_When_DensityXHigh() {
        displayMetrics.densityDpi = DisplayMetrics.DENSITY_XHIGH

        val densityQualifier = displayMetrics.getDensityQualifier()

        assertEquals("xhdpi", densityQualifier)
    }

    @Test
    fun should_ReturnXXHIGH_When_DensityXXHigh() {
        displayMetrics.densityDpi = DisplayMetrics.DENSITY_XXHIGH

        val densityQualifier = displayMetrics.getDensityQualifier()

        assertEquals("xxhdpi", densityQualifier)
    }

    @Test
    fun should_ReturnXXXHDPI_When_DensityXXXHigh() {
        displayMetrics.densityDpi = DisplayMetrics.DENSITY_XXXHIGH

        val densityQualifier = displayMetrics.getDensityQualifier()

        assertEquals("xxxhdpi", densityQualifier)
    }

    @Test
    fun should_ReturnMDPI_When_UndefinedDensity() {
        displayMetrics.densityDpi = 0

        val densityQualifier = displayMetrics.getDensityQualifier()

        assertEquals("mdpi", densityQualifier)
    }
}