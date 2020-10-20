package com.cuidedacidade.data.repository

import com.cuidedacidade.data.entity.CategoryEntity
import com.cuidedacidade.data.exception.FirebaseUnspecifiedException
import com.cuidedacidade.data.extensions.getSync
import com.cuidedacidade.data.extensions.toObjectsWithId
import com.cuidedacidade.data.mapper.CategoryEntityDataMapper
import com.cuidedacidade.data.repository.collections.CategoriesCollection
import com.cuidedacidade.domain.entity.Category
import com.cuidedacidade.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CategoryDataRepository @Inject constructor(
    private val categoryEntityDataMapper: CategoryEntityDataMapper
) : BaseRepository(), CategoryRepository {
    override fun getCategories(): Single<MutableList<Category>> {
        return Single.fromCallable {
            db.collection(CategoriesCollection.name).getSync().let { categories ->
                if (categories.isSuccessful && categories.result != null) {
                    categories.result!!.toObjectsWithId(CategoryEntity::class.java).let {
                        categoryEntityDataMapper.transform(it)
                    }
                } else {
                    throw categories.exception ?: FirebaseUnspecifiedException()
                }
            }
        }
    }
}