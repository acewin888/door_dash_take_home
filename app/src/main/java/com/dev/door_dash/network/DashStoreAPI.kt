package com.dev.door_dash.network

import com.dev.door_dash.data.DashResponse
import io.reactivex.Single

/**
 *  the API for Door dash restaurant list
 */
interface DashStoreAPI {

    /**
     * Get door dash nearby restaurants response
     */
    fun getStoreSummary(): Single<DashResponse>
}