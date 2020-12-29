package com.dev.door_dash.data

/**
 * Store data
 */
data class DashStore(
    val id: Int,
    val average_rating: Float,
    val description: String,
    val cover_img_url: String,
    val header_img_url: String,
    val name: String,
    val next_close_time: String,
    val next_open_time: String,
    val distance_from_consumer: String
)