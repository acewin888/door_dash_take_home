package com.dev.door_dash.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Detail restaurant data
 * from end point  /v2/restaurant/<restaurant_id>/
 */

@Parcelize
@JsonClass(generateAdapter = true)
class DashDetail(
    @Json(name = "phone_number")
    val phone_number: String
) : Parcelable