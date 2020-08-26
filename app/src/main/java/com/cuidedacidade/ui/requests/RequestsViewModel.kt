package com.cuidedacidade.ui.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cuidedacidade.base.Resource
import com.cuidedacidade.data.mapper.RequestEntityDataMapper
import com.cuidedacidade.data.repository.RequestDataRepository
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.usecase.GetPendingRequestsUseCase
import com.cuidedacidade.log.Log
import com.cuidedacidade.rx.AppSchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

class RequestsViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val requests: MutableLiveData<Resource<List<Request>>> by lazy {
        val liveData = MutableLiveData<Resource<List<Request>>>()
        loadRequests(liveData)
        liveData
    }

    fun getRequests(): LiveData<Resource<List<Request>>> {
        return requests
    }

    private fun loadRequests(liveData: MutableLiveData<Resource<List<Request>>>) {
        //TODO Isso deve ser via DI
        val requestDataRepository = RequestDataRepository(RequestEntityDataMapper())
        val schedulerProvider = AppSchedulerProvider()

        val getPendingRequestsUseCase = GetPendingRequestsUseCase(requestDataRepository)

        //TODO dados de teste
        val subscriptionGetPendingRequestsUseCase = getPendingRequestsUseCase("teste")
            .retry(1)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                {
                    //TODO observeOn vs postValue
                    ///TODO Tranformar para model de apresentação?
                    liveData.value = Resource.Success(it)
                },
                {
                    Log.exception(it)
                    liveData.value = Resource.Error(null, it)
                })
        compositeDisposable.add(subscriptionGetPendingRequestsUseCase)
    }

    override fun onCleared() {
        super.onCleared()

        //TODO Isso esta correto?
        compositeDisposable.clear()
    }
}