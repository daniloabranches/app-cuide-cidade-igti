package com.cuidedacidade.ui.requestdetails

import androidx.lifecycle.MutableLiveData
import com.cuidedacidade.base.BaseViewModel
import com.cuidedacidade.core.flow.Resource
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.exception.ValidationException
import com.cuidedacidade.domain.usecase.SaveRequestUseCase
import com.cuidedacidade.log.Log
import com.cuidedacidade.rx.SchedulerProvider
import com.cuidedacidade.security.Auth
import com.cuidedacidade.ui.categories.model.CategoryModel
import java.util.*
import javax.inject.Inject

private const val NEW_REQUEST_ID = ""

class RequestDetailsViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val saveRequestUseCase: SaveRequestUseCase
) : BaseViewModel() {
    fun saveRequest(category: CategoryModel, description: String): MutableLiveData<Resource<Unit>> {
        clearCompositeDisposable()
        val liveData = MutableLiveData<Resource<Unit>>()
        val request = createRequest(category, description)
        val subscriptionSaveRequestUseCase = saveRequestUseCase(Auth.USER, request)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                {
                    liveData.value = Resource.Loading()
                },
                {
                    if (it !is ValidationException) {
                        Log.exception(it)
                    }
                    liveData.value = Resource.Error(null, it)
                },
                {
                    liveData.value = Resource.Success(Unit)
                })
        addCompositeDisposable(subscriptionSaveRequestUseCase)
        return liveData
    }

    private fun createRequest(category: CategoryModel, description: String) =
        Request(
            NEW_REQUEST_ID,
            category.title,
            description,
            category.image,
            Date(),
            Request.Status.PENDING
        )
}