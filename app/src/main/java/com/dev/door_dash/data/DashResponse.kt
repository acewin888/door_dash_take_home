package com.dev.door_dash.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Response object for Door Dash Restaurant list
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class DashResponse(
    @Json(name = "num_results")
    val num_results: Int = 0,
    @Json(name = "is_first_time_user")
    val is_first_time_user: Boolean = false,
    @Json(name = "sort_order")
    val sort_order: String = "",
    @Json(name = "next_offset")
    val next_offset: Int = 0,
    @Json(name = "show_list_as_pickup")
    val show_list_as_pickup: Boolean = false,
    @Json(name = "stores")
    val stores: List<DashStore> = emptyList()
) : Parcelable