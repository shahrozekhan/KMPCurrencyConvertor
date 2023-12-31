package com.multiplatform.kmmcc.domain.model

import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY
import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY_NAME
import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY_RATE
import com.multiplatform.kmmcc.data.dto.ExchangeRateDto
import database.ExchangeRateEntity

data class ExchangeRate(val currency: String, val currencyName: String, val rate: Double) {
    companion object
}

fun ExchangeRate.toExchangeRateEntity(): ExchangeRateEntity {
    return ExchangeRateEntity(
        currency,
        currencyName,
        rate,
        false
    )
}

fun ExchangeRateDto.toExchangeRateEntity(): ExchangeRateEntity {
    return ExchangeRateEntity(
        currency,
        currencyName,
        rate,
        selected
    )
}

fun ExchangeRateEntity.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        currency,
        currencyName ?: "",
        rate
    )
}

fun ExchangeRateDto.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        currency,
        currencyName ?: "",
        rate
    )
}

val ExchangeRate.Companion.appDefaultExchangeRate: ExchangeRate
    get() {
        return ExchangeRate(
            DEFAULT_CURRENCY,
            DEFAULT_CURRENCY_NAME,
            DEFAULT_CURRENCY_RATE
        )
    }

