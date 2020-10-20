package com.cuidedacidade.ui.requestdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuidedacidade.base.BaseViewModel
import com.cuidedacidade.core.flow.Resource
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.exception.ValidationException
import com.cuidedacidade.domain.usecase.SaveRequestUseCase
import com.cuidedacidade.log.Log
import com.cuidedacidade.rx.SchedulerProvider
import com.cuidedacidade.security.Auth
import com.cuidedacidade.ui.categories.CategoryBundle
import java.util.*
import javax.inject.Inject

class RequestDetailsViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val saveRequestUseCase: SaveRequestUseCase
) : BaseViewModel() {
    fun saveRequest(categoryBundle: CategoryBundle, description: String): LiveData<Resource<Unit>> {
        clearCompositeDisposable()
        val liveData = MutableLiveData<Resource<Unit>>()
        val request = createRequest(categoryBundle, description)
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
        addInCompositeDisposable(subscriptionSaveRequestUseCase)
        return liveData
    }

    private fun createRequest(categoryBundle: CategoryBundle, description: String): Request {
        val newRequestId = ""
        return Request(
            newRequestId,
            categoryBundle.title,
            description,
            categoryBundle.image,
            Date(),
            Request.Status.PENDING
        )
    }
}