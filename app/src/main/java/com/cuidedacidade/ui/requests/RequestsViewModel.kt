package com.cuidedacidade.ui.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cuidedacidade.data.repository.RequestDataRepository
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.usecase.GetPendingRequestsUseCase
import com.cuidedacidade.log.Log
import com.cuidedacidade.rx.AppSchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

class RequestsViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val requests: MutableLiveData<List<Request>> by lazy {
        val liveData = MutableLiveData<List<Request>>()
        loadRequests(liveData)
        liveData
    }

    fun getRequests(): LiveData<List<Request>> {
        return requests
    }

    private fun loadRequests(liveData: MutableLiveData<List<Request>>) {
        //TODO Isso deve ser via DI
        val requestDataRepository = RequestDataRepository()
        val schedulerProvider = AppSchedulerProvider()

        val getPendingRequestsUseCase = GetPendingRequestsUseCase(requestDataRepository)

        val subscriptionGetPendingRequestsUseCase = getPendingRequestsUseCase()
            .retry(1)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                {
                    //TODO observeOn vs postValue
                    ///TODO Tranformar para model de apresentação?
                    liveData.value = it
                },
                {
                    Log.exception(it)
                    //TODO O que fazer com a excecao?
                })
        compositeDisposable.add(subscriptionGetPendingRequestsUseCase)
    }

    override fun onCleared() {
        super.onCleared()

        //TODO Isso esta correto?
        compositeDisposable.clear()
    }
}