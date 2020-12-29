package com.dev.door_dash.repo

import com.dev.door_dash.data.DashStore
import com.dev.door_dash.network.NetworkingManager
import io.reactivex.Single

/**
 * Implementation for [DashRepo]
 */
class DashRepoImpl(
    private val networkManager: NetworkingManager
) : DashRepo {


    override fun getRestaurants(): Single<List<DashStore>> {
        return networkManager.getDashStoreAPI().getStoreSummary().map { it.stores }
    }
}