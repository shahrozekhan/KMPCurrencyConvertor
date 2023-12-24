package com.multiplatform.kmmcc.domain.model

import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY
import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY_NAME
import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY_RATE
import com.multiplatform.kmmcc.data.dto.ExchangeRateDto

data class ExchangeRate(val currency: String, val currencyName: String, val rate: Double) {
    companion object
}

fun ExchangeRate.toExchangeRateDto(): ExchangeRateDto {
    return ExchangeRateDto(
        currency,
        currencyName,
        rate,
        false
    )
}

fun ExchangeRateDto.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        currency,
        currencyName,
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

