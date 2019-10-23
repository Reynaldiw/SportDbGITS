package com.reynaldiwijaya.sportdbgits.base.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.clear()
        }
    }

    abstract fun onError(e : Throwable)
}