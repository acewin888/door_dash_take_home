package com.dev.door_dash.summary_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.door_dash.data.DashStore
import com.dev.door_dash.data.ErrorType
import com.dev.door_dash.repo.DashRepo
import com.dev.door_dash.rxSchedulers.RxScheduler
import io.reactivex.disposables.CompositeDisposable

/**
 * ViewModel for Summary Screen
 */
class SummaryViewModel(
    private val repo: DashRepo,
    private val rxScheduler: RxScheduler,
    private val subscription: CompositeDisposable
) : ViewModel() {

    val storesLiveData: MutableLiveData<List<DashStore>> = MutableLiveData()
    val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    //TODO convert it to single Event
    val errorLiveData: MutableLiveData<ErrorType> = MutableLiveData()


    fun getNearbyRestaurants() {
        subscription.add(
            repo.getRestaurants()
                .subscribeOn(rxScheduler.getIO())
                .observeOn(rxScheduler.getMain())
                .subscribe(
                    {
                        progressLiveData.value = false
                        storesLiveData.value = it
                    },
                    {
                        progressLiveData.value = false
                        errorLiveData.value = ErrorType.Network(it.toString())
                    }
                )
        )
    }


    override fun onCleared() {
        subscription.clear()
        super.onCleared()
    }
}