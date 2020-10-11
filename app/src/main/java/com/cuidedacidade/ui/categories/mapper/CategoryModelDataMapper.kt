package com.cuidedacidade.ui.categories.mapper

import com.cuidedacidade.base.ModelDataMapper
import com.cuidedacidade.domain.entity.Category
import com.cuidedacidade.ui.categories.model.CategoryModel
import javax.inject.Inject

class CategoryModelDataMapper @Inject constructor() :
    ModelDataMapper<List<Category>, List<CategoryModel>> {

    override fun apply(categories: List<Category>): List<CategoryModel> =
        categories.map {
            transform(it)
        }.toMutableList()

    private fun transform(category: Category) =
        CategoryModel(
            category.id,
            category.title,
            category.image
        )
}