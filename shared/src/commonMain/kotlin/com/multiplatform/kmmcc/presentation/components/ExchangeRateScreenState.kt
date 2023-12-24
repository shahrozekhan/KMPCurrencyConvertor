package com.multiplatform.kmmcc.presentation.components

import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.domain.model.appDefaultExchangeRate

data class ExchangeRateScreenState(
    val isCurrenciesLoading: Boolean = false,
    val isFavoriteLoading: Boolean = false,
    val isConverting: Boolean = false,
    val isForceSyncingExchangeRate: Boolean = false,
    val listOfCurrency: List<ExchangeRate>? = listOf(),
    val favoriteCurrencies: List<ExchangeRate>? = listOf(),
    val listOfConvertedAgainstBase: List<Pair<ExchangeRate, Double>> = listOf(),
    val fromCurrency: ExchangeRate = ExchangeRate.appDefaultExchangeRate,
    val errorMessage: String = "",
    val amount: String = "100"
)
