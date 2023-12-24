package com.multiplatform.kmmcc.common.dto


import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: Error,
    val success: Boolean
)