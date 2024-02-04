package com.multiplatform.kmmcc.presentation.components.conversion

import com.multiplatform.kmmcc.domain.model.ExchangeRate


sealed class CurrencyConversionScreenEvent {
    data class EnteredAmount(val value: String) : CurrencyConversionScreenEvent()
    data class SelectFromCurrency(val value: ExchangeRate) : CurrencyConversionScreenEvent()
    data class SelectToCurrency(val value: ExchangeRate) : CurrencyConversionScreenEvent()
    data class RemoveCurrencyFromExchangeRates(val index: Int, val value: ExchangeRate) :
        CurrencyConversionScreenEvent()
    data class RemoveCurrencyToExchangeRates(val index: Int, val value: ExchangeRate) :
        CurrencyConversionScreenEvent()
    object ConvertCurrencyConversion : CurrencyConversionScreenEvent()
    object ForceSyncCurrencyConversion : CurrencyConversionScreenEvent() {

    }
}