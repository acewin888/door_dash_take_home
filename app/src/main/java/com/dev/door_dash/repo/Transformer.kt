package com.dev.door_dash.repo

import com.dev.door_dash.data.DashStore
import com.dev.door_dash.data.DashStoreItem
import com.dev.door_dash.data.StoreStatus

/**
 * Transformer that convert data for API to domain specific data
 */
class Transformer {

    companion object {
        private const val CLOSE_STATUS = "Closed"
        private const val MIN = " Mins"
    }

    /**
     * Transform [DashStore] to [DashStoreItem]
     */
    fun transform(store: DashStore): DashStoreItem {
        return DashStoreItem(
            image_url = store.cover_img_url,
            name = store.name,
            short_description = getDescription(store.description.split(",").toTypedArray()),
            location_status = getStatus(store.status)
        )
    }

    private fun getDescription(list: Array<String>): String {
        return when (list.size) {
            0 -> ""
            1 -> list[0]
            else -> list[0] + "," + list[1]
        }
    }

    private fun getStatus(status: StoreStatus): String {
        return when (status.asap_available) {
            true -> status.asap_minutes_range[0].toString() + MIN
            false -> CLOSE_STATUS
        }
    }
}