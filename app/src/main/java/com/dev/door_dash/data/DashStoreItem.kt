package com.dev.door_dash.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * the Store info that [StoreAdapter] receives and display to user
 */
@Parcelize
data class DashStoreItem(
    val image_url: String,
    val name: String,
    val short_description: String,
    val location_status: String
) : Parcelable