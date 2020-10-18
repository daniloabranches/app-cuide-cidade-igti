package com.cuidedacidade.ui.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuidedacidade.base.BaseViewModel
import com.cuidedacidade.core.flow.Resource
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.usecase.GetAllRequestsUseCase
import com.cuidedacidade.domain.usecase.GetPendingRequestsUseCase
import com.cuidedacidade.log.Log
import com.cuidedacidade.rx.SchedulerProvider
import com.cuidedacidade.security.Auth
import com.cuidedacidade.ui.requests.mapper.RequestModelDataMapper
import com.cuidedacidade.ui.requests.model.RequestModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RequestsViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val requestModelDataMapper: RequestModelDataMapper,
    private val getPendingRequestsUseCase: GetPendingRequestsUseCase,
    private val getAllRequestsUseCase: GetAllRequestsUseCase
) : BaseViewModel() {
    private val pendingRequests: MutableLiveData<Resource<List<RequestModel>>> by lazy {
        val liveData = MutableLiveData<Resource<List<RequestModel>>>()
        loadPendingRequests(liveData)
        liveData
    }

    private val allRequests: MutableLiveData<Resource<List<RequestModel>>> by lazy {
        val liveData = MutableLiveData<Resource<List<RequestModel>>>()
        loadAllRequests(liveData)
        liveData
    }

    fun getPendingRequests(): LiveData<Resource<List<RequestModel>>> = pendingRequests

    fun refreshPendingRequests() = loadPendingRequests(pendingRequests)

    private fun loadPendingRequests(liveData: MutableLiveData<Resource<List<RequestModel>>>) =
        loadRequests(liveData, getPendingRequestsUseCase(Auth.USER))

    fun getAllRequests(): LiveData<Resource<List<RequestModel>>> = allRequests

    fun refreshAllRequests() = loadAllRequests(allRequests)

    private fun loadAllRequests(liveData: MutableLiveData<Resource<List<RequestModel>>>) =
        loadRequests(liveData, getAllRequestsUseCase(Auth.USER))

    private fun loadRequests(
        liveData: MutableLiveData<Resource<List<RequestModel>>>,
        useCase: Single<MutableList<Request>>
    ) {
        clearCompositeDisposable()

        liveData.value = Resource.Loading()

        val subscriptionGetPendingRequestsUseCase = useCase
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .map(requestModelDataMapper)
            .subscribe(
                {
                    liveData.value = Resource.Success(it)
                },
                {
                    Log.exception(it)
                    liveData.value = Resource.Error(null, it)
                })

        addCompositeDisposable(subscriptionGetPendingRequestsUseCase)
    }
}