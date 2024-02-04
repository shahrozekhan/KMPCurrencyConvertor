package com.multiplatform.kmmcc.domain.model

import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY
import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY_NAME
import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY_RATE
import com.multiplatform.kmmcc.data.dto.ExchangeRateDto
import database.ExchangeRateEntity
import database.FromEntity
import database.ToEntity

data class ExchangeRate(val currency: String, val currencyName: String, val rate: Double) {
    companion object
}

fun ExchangeRate.toExchangeRateEntity(): ExchangeRateEntity {
    return ExchangeRateEntity(
        currency,
        currencyName,
        rate
    )
}

fun ExchangeRate.toToEntity(): ToEntity {
    return ToEntity(
        currency,
        currencyName,
        rate
    )
}

fun ExchangeRate.toFromEntity(): FromEntity {
    return FromEntity(
        currency,
        currencyName,
        rate
    )
}

fun ExchangeRateDto.toExchangeRateEntity(): ExchangeRateEntity {
    return ExchangeRateEntity(
        currency,
        currencyName,
        rate
    )
}

fun ExchangeRateEntity.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        currency,
        currencyName ?: "",
        rate
    )
}

fun ToEntity.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        currency,
        currencyName ?: "",
        rate
    )
}

fun FromEntity.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        currency,
        currencyName ?: "",
        rate
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

