package com.cuidedacidade.data.repository

import com.cuidedacidade.data.entity.CategoryEntity
import com.cuidedacidade.data.exception.FirebaseUnspecifiedException
import com.cuidedacidade.data.extensions.toObjectsWithId
import com.cuidedacidade.data.mapper.CategoryEntityDataMapper
import com.cuidedacidade.domain.entity.Category
import com.cuidedacidade.domain.repository.CategoryRepository
import com.google.android.gms.tasks.Tasks
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CategoryDataRepository @Inject constructor(
    private val categoryEntityDataMapper: CategoryEntityDataMapper
) : BaseRepository(), CategoryRepository {

    override fun getCategories(): Single<MutableList<Category>> {
        return Single.fromCallable {
            val categories = db.collection("categories").get()

            Tasks.await(categories)

            if (categories.isSuccessful && categories.result != null) {
                val dataCategories = categories.result!!.toObjectsWithId(CategoryEntity::class.java)
                categoryEntityDataMapper.transform(dataCategories)
            } else {
                throw categories.exception ?: FirebaseUnspecifiedException()
            }
        }
    }
}