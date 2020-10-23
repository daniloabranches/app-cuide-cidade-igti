package com.cuidedacidade.data.entity

import java.util.*

class RequestEntity() : BaseEntity() {
    lateinit var category_name: String
    lateinit var description: String
    lateinit var image: String
    lateinit var date: Date
    var completion_date: Date? = null
    var status: Int = 0
}