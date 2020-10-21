package com.cuidedacidade.feature.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class RequestBundle(
    val categoryName: String,
    val description: String,
    val date: Date,
    val status: Status
) : Parcelable {
    enum class Status(val value: Int) {
        PENDING(1),
        EXECUTED(2);

        companion object {
            fun valueOf(value: Int) = values().first { it.value == value }
        }
    }
}