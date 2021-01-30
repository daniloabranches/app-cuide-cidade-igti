package com.cuidedacidade.core.extensions

import android.util.DisplayMetrics

const val MEDIUM_DENSITY_QUALIFIER = "mdpi"

fun DisplayMetrics.getDensityQualifier(): String {
    return when (densityDpi) {
        DisplayMetrics.DENSITY_LOW -> "ldpi"
        DisplayMetrics.DENSITY_MEDIUM -> MEDIUM_DENSITY_QUALIFIER
        DisplayMetrics.DENSITY_HIGH -> "hdpi"
        DisplayMetrics.DENSITY_XHIGH -> "xhdpi"
        DisplayMetrics.DENSITY_XXHIGH -> "xxhdpi"
        DisplayMetrics.DENSITY_XXXHIGH -> "xxxhdpi"
        else -> MEDIUM_DENSITY_QUALIFIER
    }
}