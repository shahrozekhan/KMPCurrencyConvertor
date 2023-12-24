package com.multiplatform.kmmcc.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateDto(
    val currency: String,
    val currencyName: String,
    val rate: Double,
    var selected: Boolean
)