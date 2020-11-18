package com.cuidedacidade.features.allrequests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuidedacidade.core.BaseViewModel
import com.cuidedacidade.core.auth.AuthManager
import com.cuidedacidade.core.network.Resource
import com.cuidedacidade.core.task.SchedulerProvider
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.usecase.GetAllRequestsUseCase
import com.cuidedacidade.log.Log
import javax.inject.Inject

class AllRequestsViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val getAllRequestsUseCase: GetAllRequestsUseCase,
    private val appAuthManager: AuthManager
) : BaseViewModel() {
    private val allRequests: MutableLiveData<Resource<List<Request>>> by lazy {
        val liveData = MutableLiveData<Resource<List<Request>>>()
        loadAllRequests(liveData)
        liveData
    }

    fun getAllRequests(): LiveData<Resource<List<Request>>> = allRequests

    fun refreshAllRequests() = loadAllRequests(allRequests)

    private fun loadAllRequests(liveData: MutableLiveData<Resource<List<Request>>>) {
        clearCompositeDisposable()

        liveData.value = Resource.Loading()

        val subscriptionGetPendingRequestsUseCase =
            getAllRequestsUseCase(appAuthManager.getUserId())
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