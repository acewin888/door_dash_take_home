package com.dev.door_dash.rxSchedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Testing Schedulers for RxJava for unit test
 */
class TestScheduler : RxScheduler {

    override fun getMain(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun getIO(): Scheduler {
        return Schedulers.trampoline()
    }
}