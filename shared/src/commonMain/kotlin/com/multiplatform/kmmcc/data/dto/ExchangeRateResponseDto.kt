package com.multiplatform.kmmcc.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateResponseDto(
    val base: String? = null,
    val date: String? = null,
    val rates: HashMap<String, Double>? = null,
    val symbols: HashMap<String, String>? = null,
    val success: Boolean,
    val timestamp: Int? = null
)