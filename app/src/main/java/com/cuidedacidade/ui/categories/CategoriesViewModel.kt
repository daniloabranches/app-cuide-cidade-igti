package com.cuidedacidade.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuidedacidade.base.BaseViewModel
import com.cuidedacidade.core.flow.Resource
import com.cuidedacidade.domain.entity.Category
import com.cuidedacidade.domain.usecase.GetCategoriesUseCase
import com.cuidedacidade.log.Log
import com.cuidedacidade.rx.SchedulerProvider
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel() {
    private val categories: MutableLiveData<Resource<List<Category>>> by lazy {
        val liveData = MutableLiveData<Resource<List<Category>>>()
        loadCategories(liveData)
        liveData
    }

    fun getCategories(): LiveData<Resource<List<Category>>> = categories

    private fun loadCategories(liveData: MutableLiveData<Resource<List<Category>>>) {
        clearCompositeDisposable()

        liveData.value = Resource.Loading()

        val subscriptioGetCategoriesUseCase = getCategoriesUseCase()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                {
                    liveData.value = Resource.Success(it)
                },
                {
                    Log.exception(it)
                    liveData.value = Resource.Error(null, it)
                })

        addInCompositeDisposable(subscriptioGetCategoriesUseCase)
    }
}