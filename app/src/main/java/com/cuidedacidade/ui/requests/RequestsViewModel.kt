package com.cuidedacidade.ui.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cuidedacidade.base.Resource
import com.cuidedacidade.data.mapper.RequestEntityDataMapper
import com.cuidedacidade.data.repository.RequestDataRepository
import com.cuidedacidade.domain.usecase.GetPendingRequestsUseCase
import com.cuidedacidade.log.Log
import com.cuidedacidade.mapper.RequestModelDataMapper
import com.cuidedacidade.model.RequestModel
import com.cuidedacidade.rx.AppSchedulerProvider
import com.cuidedacidade.security.Auth
import io.reactivex.rxjava3.disposables.CompositeDisposable

class RequestsViewModel : ViewModel() {
    //TODO DI
    private val requestDataRepository = RequestDataRepository(RequestEntityDataMapper())
    private val schedulerProvider = AppSchedulerProvider()
    private val requestModelDataMapper = RequestModelDataMapper()
    private val getPendingRequestsUseCase = GetPendingRequestsUseCase(requestDataRepository)

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