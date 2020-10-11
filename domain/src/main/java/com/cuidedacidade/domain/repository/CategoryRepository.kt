package com.cuidedacidade.domain.repository

import com.cuidedacidade.domain.entity.Category
import io.reactivex.rxjava3.core.Single

interface CategoryRepository {
    fun getCategories(): Single<MutableList<Category>>
}