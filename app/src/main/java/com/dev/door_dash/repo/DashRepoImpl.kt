package com.dev.door_dash.repo

import com.dev.door_dash.data.DashStoreItem
import com.dev.door_dash.network.NetworkingManager
import io.reactivex.Single

/**
 * Implementation for [DashRepo]
 */
class DashRepoImpl(
    private val networkManager: NetworkingManager,
    private val transformer: Transformer = Transformer()
) : DashRepo {


    override fun getRestaurants(): Single<List<DashStoreItem>> {
        return networkManager.getDashStoreAPI().getStoreSummary()
            .map { it.stores }
            .flatMapIterable { it }
            .map { transformer.transform(it) }
            .toList()
    }
}