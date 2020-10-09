package com.cuidedacidade.ui.requests.model

import java.util.*

data class RequestModel(
    val id: String,
    val categoryName: String,
    val description: String,
    val image: String,
    val date: Date,
    val status: Status
) {
    enum class Status(val value: Int) {
        PENDING(1),
        EXECUTED(2);

        companion object {
            fun valueOf(value: Int) = values().first { it.value == value }
        }
    }
}