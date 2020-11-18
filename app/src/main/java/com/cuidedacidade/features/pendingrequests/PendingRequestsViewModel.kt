package com.cuidedacidade.features.pendingrequests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuidedacidade.core.BaseViewModel
import com.cuidedacidade.core.auth.AuthManager
import com.cuidedacidade.core.network.Resource
import com.cuidedacidade.core.task.SchedulerProvider
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.usecase.GetPendingRequestsUseCase
import com.cuidedacidade.log.Log
import javax.inject.Inject

class PendingRequestsViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val getPendingRequestsUseCase: GetPendingRequestsUseCase,
    private val appAuthManager: AuthManager
) : BaseViewModel() {
    private val pendingRequests: MutableLiveData<Resource<List<Request>>> by lazy {
        val liveData = MutableLiveData<Resource<List<Request>>>()
        loadPendingRequests(liveData)
        liveData
    }

    fun getPendingRequests(): LiveData<Resource<List<Request>>> = pendingRequests

    fun refreshPendingRequests() = loadPendingRequests(pendingRequests)

    private fun loadPendingRequests(
        liveData: MutableLiveData<Resource<List<Request>>>
    ) {
        clearCompositeDisposable()

        liveData.value = Resource.Loading()

        val subscriptionGetPendingRequestsUseCase =
            getPendingRequestsUseCase(appAuthManager.getUserId())
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

        addInCompositeDisposable(subscriptionGetPendingRequestsUseCase)
    }
}