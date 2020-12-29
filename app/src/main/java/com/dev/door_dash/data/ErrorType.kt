package com.dev.door_dash.data

/**
 * Simple Wrapper for ErrorType
 */
sealed class ErrorType {

    /**
     * NetWork Error
     * @param error the Network Error trace
     */
    data class Network(val error: String) : ErrorType()

    /**
     * Generic Error Type
     */
    object Generic : ErrorType()
}