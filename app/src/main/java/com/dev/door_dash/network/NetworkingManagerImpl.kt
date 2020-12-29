package com.dev.door_dash.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * the Implementation class for [NetworkingManager]
 */
class NetworkingManagerImpl : NetworkingManager {

    companion object {
        private const val BASE_URL = "https://api.doordash.com/"
    }

    private fun getRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    override fun getDashStoreAPI(): DashStoreAPI {
        return getRetroFit().create(DashStoreAPI::class.java)
    }
}