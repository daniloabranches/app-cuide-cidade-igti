package com.cuidedacidade.ui.requests

import java.util.*

data class Request(
    val categoryName: String,
    val description: String,
    val image: String,
    val date: Date
)