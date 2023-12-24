package com.multiplatform.kmmcc.common.dto

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val code: String,
    val info: String,
    val message: String
)