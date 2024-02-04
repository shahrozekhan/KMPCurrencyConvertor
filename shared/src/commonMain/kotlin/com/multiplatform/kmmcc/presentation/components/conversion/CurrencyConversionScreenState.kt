package com.multiplatform.kmmcc.presentation.components.conversion

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.domain.model.appDefaultExchangeRate

data class CurrencyConversionScreenState(
    val isCurrenciesLoading: Boolean = false,
    val isFavoriteLoading: Boolean = false,
    val isConverting: Boolean = false,
    val isForceSyncingExchangeRate: Boolean = false,
    val listOfCurrency: List<ExchangeRate>? = listOf(),
    val convertedExchangeRate: List<Pair<ExchangeRate, List<Pair<ExchangeRate, BigDecimal>>>> = listOf(),
    val fromFavorites: List<ExchangeRate> = listOf(),
    val toFavorites: List<ExchangeRate> = listOf(),
    val errorMessage: String = "",
    val amount: String = ""
)
