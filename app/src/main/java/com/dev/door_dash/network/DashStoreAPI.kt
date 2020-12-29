package com.dev.door_dash.network

import com.dev.door_dash.data.DashResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 *  the API for Door dash restaurant list
 */
interface DashStoreAPI {

    /**
     * Get door dash nearby restaurants response
     */
    @GET("v1/store_feed/?lat=37.422740&lng=-122.139956&offset=0&limit=10")
    fun getStoreSummary(): Single<DashResponse>
}