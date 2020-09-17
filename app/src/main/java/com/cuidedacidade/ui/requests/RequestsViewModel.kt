package com.cuidedacidade.ui.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cuidedacidade.core.flow.Resource
import com.cuidedacidade.domain.usecase.GetPendingRequestsUseCase
import com.cuidedacidade.log.Log
import com.cuidedacidade.mapper.RequestModelDataMapper
import com.cuidedacidade.model.RequestModel
import com.cuidedacidade.rx.SchedulerProvider
import com.cuidedacidade.security.Auth
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class RequestsViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val requestModelDataMapper: RequestModelDataMapper,
    private val getPendingRequestsUseCase: GetPendingRequestsUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val requests: MutableLiveData<Resource<List<RequestModel>>> by lazy {
        val liveData = MutableLiveData<Resource<List<RequestModel>>>()
        loadRequests(liveData)
        liveData
    }

    private fun loadRequests(liveData: MutableLiveData<Resource<List<RequestModel>>>) {
        liveData.value = Resource.Loading()

        val subscriptionGetPendingRequestsUseCase = getPendingRequestsUseCase(Auth.USER)
            .retry(1)
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

        compositeDisposable.add(subscriptionGetPendingRequestsUseCase)
    }

    fun getRequests(): LiveData<Resource<List<RequestModel>>> {
        compositeDisposable.clear()
        return requests
    }

    fun refreshRequests() = loadRequests(requests)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}