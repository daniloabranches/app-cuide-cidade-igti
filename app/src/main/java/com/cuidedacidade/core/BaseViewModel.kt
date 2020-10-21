package com.cuidedacidade.core

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        clearCompositeDisposable()
    }

    fun addInCompositeDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    fun clearCompositeDisposable() = compositeDisposable.clear()
}