package com.cuidedacidade.data.mapper

import com.cuidedacidade.data.entity.CategoryEntity
import com.cuidedacidade.domain.entity.Category
import javax.inject.Inject

class CategoryEntityDataMapper @Inject constructor() {
    fun transform(dataCategories: List<CategoryEntity>) =
        dataCategories.map {
            transform(it)
        }.toMutableList()

    private fun transform(dataCategory: CategoryEntity) =
        Category(
            dataCategory.id,
            dataCategory.title,
            dataCategory.image
        )
}