package com.dev.door_dash.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Store status
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class StoreStatus(
    @Json(name = "asap_available")
    val asap_available: Boolean = false,
    @Json(name = "asap_minutes_range")
    val asap_minutes_range: List<Int> = emptyList()
) : Parcelable