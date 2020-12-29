package com.dev.door_dash.rxSchedulers

import io.reactivex.Scheduler

/**
 * The RxScheduler for RxJava, for both Production and Testing
 */
interface RxScheduler {

    /**
     * Main Scheduler
     */
    fun getMain(): Scheduler

    /**
     * IO Scheduler
     */
    fun getIO(): Scheduler
}