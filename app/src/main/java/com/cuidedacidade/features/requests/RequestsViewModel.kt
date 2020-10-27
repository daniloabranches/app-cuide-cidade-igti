package com.cuidedacidade.features.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuidedacidade.core.BaseViewModel
import com.cuidedacidade.core.auth.AuthManager
import com.cuidedacidade.core.network.Resource
import com.cuidedacidade.core.task.SchedulerProvider
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.usecase.GetAllRequestsUseCase
import com.cuidedacidade.domain.usecase.GetPendingRequestsUseCase
import com.cuidedacidade.log.Log
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RequestsViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val getPendingRequestsUseCase: GetPendingRequestsUseCase,
    private val getAllRequestsUseCase: GetAllRequestsUseCase,
    private val appAuthManager: AuthManager
) : BaseViewModel() {
    //TODO Separar viewModel dos fragments
    private val pendingRequests: MutableLiveData<Resource<List<Request>>> by lazy {
        val liveData = MutableLiveData<Resource<List<Request>>>()
        loadPendingRequests(liveData)
        liveData
    }

    private val allRequests: MutableLiveData<Resource<List<Request>>> by lazy {
        val liveData = MutableLiveData<Resource<List<Request>>>()
        loadAllRequests(liveData)
        liveData
    }

    fun getPendingRequests(): LiveData<Resource<List<Request>>> = pendingRequests

    fun refreshPendingRequests() = loadPendingRequests(pendingRequests)

    private fun loadPendingRequests(liveData: MutableLiveData<Resource<List<Request>>>) =
        loadRequests(liveData, getPendingRequestsUseCase(appAuthManager.getUserId()))

    fun getAllRequests(): LiveData<Resource<List<Request>>> = allRequests

    fun refreshAllRequests() = loadAllRequests(allRequests)

    private fun loadAllRequests(liveData: MutableLiveData<Resource<List<Request>>>) =
        loadRequests(liveData, getAllRequestsUseCase(appAuthManager.getUserId()))

    private fun loadRequests(
        liveData: MutableLiveData<Resource<List<Request>>>,
        useCase: Single<MutableList<Request>>
    ) {
        clearCompositeDisposable()

        liveData.value = Resource.Loading()

        val subscriptionGetPendingRequestsUseCase = useCase
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