package com.dev.door_dash.rxSchedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Production Scheduler for Rxjava
 */
class ProdScheduler : RxScheduler {

    override fun getMain(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun getIO(): Scheduler {
        return Schedulers.io()
    }
}