package com.dev.door_dash.network

import com.dev.door_dash.data.DashResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  the API for Door dash restaurant list
 */
interface DashStoreAPI {

    companion object {
        private const val LAT = "37.422740"
        private const val LNG = "-122.139956"
        private const val OFFSET = "0"
        private const val LIMIT = "50"
    }

    /**
     * Get door dash nearby restaurants response
     */
    @GET("v1/store_feed/")
    fun getStoreSummary(
        @Query("lat") lat: String = LAT,
        @Query("lng") lng: String = LNG,
        @Query("offset") offset: String = OFFSET,
        @Query("limit") limit: String = LIMIT
    ): Observable<DashResponse>
}