package com.dev.door_dash.summary_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.door_dash.Event.Event
import com.dev.door_dash.data.DashDetail
import com.dev.door_dash.data.DashStoreItem
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

    private val _storesLiveData: MutableLiveData<List<DashStoreItem>> = MutableLiveData()
    val storesLiveData: LiveData<List<DashStoreItem>>
        get() = _storesLiveData

    private val _progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val progressLiveData: LiveData<Boolean>
        get() = _progressLiveData

    private val _detailLiveData: MutableLiveData<Event<DashDetail>> = MutableLiveData()
    val detailLiveData: LiveData<Event<DashDetail>>
        get() = _detailLiveData

    private val _errorLiveData: MutableLiveData<ErrorType> = MutableLiveData()
    val errorLiveData: LiveData<ErrorType>
        get() = _errorLiveData


    fun getNearbyRestaurants() {
        subscription.add(
            repo.getRestaurants()
                .subscribeOn(rxScheduler.getIO())
                .observeOn(rxScheduler.getMain())
                .subscribe(
                    {
                        _progressLiveData.value = false
                        _storesLiveData.value = it
                    },
                    {
                        _progressLiveData.value = false
                        _errorLiveData.value = ErrorType.Network(it.toString())
                    }
                )
        )
    }

    fun getDetailRestaurant(id: Int) {
        subscription.add(
            repo.getRestaurant(id)
                .subscribeOn(rxScheduler.getIO())
                .observeOn(rxScheduler.getMain())
                .subscribe(
                    {
                        _detailLiveData.value = Event(it)
                    },
                    {
                        _errorLiveData.value = ErrorType.Network(it.toString())
                    }
                )
        )
    }


    override fun onCleared() {
        subscription.clear()
        super.onCleared()
    }
}