package com.dev.door_dash.repo

import com.dev.door_dash.data.DashDetail
import com.dev.door_dash.data.DashStoreItem
import io.reactivex.Single

/**
 * the Repo for retrieving info for nearby restaurants
 */
interface DashRepo {

    /**
     * Get list of nearby restaurants
     */
    fun getRestaurants(): Single<List<DashStoreItem>>

    /**
     * Get Detail for specific restaurant
     */
    fun getRestaurant(id: Int): Single<DashDetail>
}