package com.cuidedacidade.features.newrequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuidedacidade.core.BaseViewModel
import com.cuidedacidade.core.auth.AuthManager
import com.cuidedacidade.core.network.Resource
import com.cuidedacidade.core.task.SchedulerProvider
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.exception.ValidationException
import com.cuidedacidade.domain.usecase.SaveRequestUseCase
import com.cuidedacidade.features.request.CategoryBundle
import com.cuidedacidade.log.Log
import java.util.*
import javax.inject.Inject

class NewRequestViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val saveRequestUseCase: SaveRequestUseCase,
    private val appAuthManager: AuthManager
) : BaseViewModel() {
    fun saveRequest(categoryBundle: CategoryBundle, description: String, photoPath: String?):
            LiveData<Resource<Unit>> {
        clearCompositeDisposable()
        val liveData = MutableLiveData<Resource<Unit>>()
        val request = createRequest(categoryBundle, description)
        val subscriptionSaveRequestUseCase =
            saveRequestUseCase(appAuthManager.getUserId(), request, photoPath)
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
            null,
            Request.Status.PENDING
        )
    }
}