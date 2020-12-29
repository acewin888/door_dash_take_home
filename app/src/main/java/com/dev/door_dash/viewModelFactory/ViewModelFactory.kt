package com.dev.door_dash.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.door_dash.repo.DashRepo
import com.dev.door_dash.rxSchedulers.RxScheduler
import com.dev.door_dash.summary_screen.SummaryViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception

/**
 * ViewModel factory for creating viewModels
 */
class ViewModelFactory(
    private val compositeDisposable: CompositeDisposable,
    private val repo: DashRepo,
    private val rxSchedulers: RxScheduler
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            SummaryViewModel::class.java -> SummaryViewModel(
                repo,
                rxSchedulers,
                compositeDisposable
            )
            else -> throw Exception("could not get the right type of viewModel")
        }
        return viewModel as T
    }
}