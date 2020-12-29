package com.dev.door_dash.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Store data from API
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class DashStore(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "average_rating")
    val average_rating: Double = 0.0,
    @Json(name = "status")
    val status: StoreStatus = StoreStatus(),
    @Json(name = "description")
    val description: String = "",
    @Json(name = "cover_img_url")
    val cover_img_url: String = "",
    @Json(name = "header_img_url")
    val header_img_url: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "next_close_time")
    val next_close_time: String = "",
    @Json(name = "next_open_time")
    val next_open_time: String = "",
    @Json(name = "distance_from_consumer")
    val distance_from_consumer: String = ""
) : Parcelable