package com.cuidedacidade.data.entity

import java.util.*

class RequestEntity() {
    lateinit var id: String
    lateinit var category_name: String
    lateinit var description: String
    lateinit var image: String
    lateinit var date: Date
    var status: Int = 0
}