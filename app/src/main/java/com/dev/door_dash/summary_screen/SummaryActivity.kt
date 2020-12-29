package com.dev.door_dash.summary_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.door_dash.R
import com.dev.door_dash.data.DashStoreItem
import com.dev.door_dash.detail_screen.DetailActivity
import com.dev.door_dash.network.NetworkingManager
import com.dev.door_dash.network.NetworkingManagerImpl
import com.dev.door_dash.repo.DashRepo
import com.dev.door_dash.repo.DashRepoImpl
import com.dev.door_dash.rxSchedulers.ProdScheduler
import com.dev.door_dash.rxSchedulers.RxScheduler
import com.dev.door_dash.summary_screen.adapter.StoreAdapter
import com.dev.door_dash.viewModelFactory.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_summary.*
import java.util.concurrent.TimeUnit

class SummaryActivity : AppCompatActivity() {

    companion object {
        val bundleKey = "BUNDLE_KEY"
    }

    private val networkingManager: NetworkingManager by lazy { NetworkingManagerImpl() }
    private val repo: DashRepo by lazy { DashRepoImpl(networkManager = networkingManager) }
    private val rxScheduler: RxScheduler by lazy { ProdScheduler() }
    private val subscription: CompositeDisposable by lazy { CompositeDisposable() }
    private val publishSubject: PublishSubject<DashStoreItem> = PublishSubject.create()
    private val viewModelFactory: ViewModelFactory =
        ViewModelFactory(subscription, repo, rxScheduler)

    private lateinit var adapter: StoreAdapter
    private lateinit var viewModel: SummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        setupView()
        setupViewModel()
        observeData()

        viewModel.getNearbyRestaurants()
    }


    private fun setupView() {
        adapter = StoreAdapter(publishSubject = publishSubject)
        summary_store_list.adapter = adapter
        summary_store_list.layoutManager = LinearLayoutManager(this)
        summary_store_list.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SummaryViewModel::class.java)
    }

    private fun observeData() {
        viewModel.progressLiveData.observe(this, Observer {
            summary_progress_bar.visibility = when (it) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        })

        viewModel.storesLiveData.observe(this, Observer {
            adapter.updateList(it)
        })

        viewModel.detailLiveData.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra(bundleKey, it)
                }
                startActivity(intent)
            }
        })

        subscription.add(
            publishSubject
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(
                    {
                        viewModel.getDetailRestaurant(it.id)
                    },
                    {
                        Log.d("door dash", "rx went wrong")
                    }
                )
        )

    }
}