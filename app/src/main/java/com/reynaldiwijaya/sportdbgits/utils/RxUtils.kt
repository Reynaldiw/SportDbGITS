package com.reynaldiwijaya.sportdbgits.utils

import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> singleScheduler(subscribeScheduler: Scheduler = Schedulers.io(), observerScheduler : Scheduler = AndroidSchedulers.mainThread()) : SingleTransformer<T, T> {
        return SingleTransformer {single ->
            single.subscribeOn(subscribeScheduler)
                .observeOn(observerScheduler)
        }
}