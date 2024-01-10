package com.multiplatform.kmmcc.common.dto


import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: Error? = null,
    val success: Boolean? = false,
    val httpStatus: Int = 0
)