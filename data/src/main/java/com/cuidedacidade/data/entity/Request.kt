package com.cuidedacidade.data.entity

import java.util.*

//TODO
data class Request(var id: String) {
    constructor() : this("")

    lateinit var categoryName: String
    lateinit var description: String
    lateinit var image: String
    lateinit var date: Date
}