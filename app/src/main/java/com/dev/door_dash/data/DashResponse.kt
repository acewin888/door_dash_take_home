package com.dev.door_dash.data

/**
 * Response object for Door Dash Restaurant list
 */
data class DashResponse(
    val num_results: Int,
    val is_first_time_user: Boolean,
    val sort_order: String,
    val next_offset: Int,
    val show_list_as_pickup: Boolean,
    val stores: List<DashStore>
)