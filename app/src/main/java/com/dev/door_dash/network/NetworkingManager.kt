package com.dev.door_dash.network

/**
 * The Networking manager
 */
interface NetworkingManager {

    /**
     * Get [DashStoreAPI] to get response
     */
    fun getDashStoreAPI(): DashStoreAPI
}