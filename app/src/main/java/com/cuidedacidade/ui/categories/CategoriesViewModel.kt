package com.cuidedacidade.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuidedacidade.base.BaseViewModel
import com.cuidedacidade.core.flow.Resource
import com.cuidedacidade.domain.usecase.GetCategoriesUseCase
import com.cuidedacidade.log.Log
import com.cuidedacidade.rx.SchedulerProvider
import com.cuidedacidade.ui.categories.mapper.CategoryModelDataMapper
import com.cuidedacidade.ui.categories.model.CategoryModel
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val categoryModelDataMapper: CategoryModelDataMapper,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel() {
    private val categories: MutableLiveData<Resource<List<CategoryModel>>> by lazy {
        val liveData = MutableLiveData<Resource<List<CategoryModel>>>()
        loadCategories(liveData)
        liveData
    }

    fun getCategories(): LiveData<Resource<List<CategoryModel>>> = categories

    private fun loadCategories(liveData: MutableLiveData<Resource<List<CategoryModel>>>) {
        clearCompositeDisposable()

        liveData.value = Resource.Loading()

        val subscriptioGetCategoriesUseCase = getCategoriesUseCase()
            .retry(1)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .map(categoryModelDataMapper)
            .subscribe(
                {
                    liveData.value = Resource.Success(it)
                },
                {
                    Log.exception(it)
                    liveData.value = Resource.Error(null, it)
                })

        addCompositeDisposable(subscriptioGetCategoriesUseCase)
    }
}