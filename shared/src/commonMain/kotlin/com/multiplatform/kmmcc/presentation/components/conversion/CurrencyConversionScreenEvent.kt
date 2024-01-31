package com.multiplatform.kmmcc.presentation.components.conversion

import com.multiplatform.kmmcc.domain.model.ExchangeRate


sealed class CurrencyConversionScreenEvent {
    data class EnteredAmount(val value: String) : CurrencyConversionScreenEvent()
    data class SelectFromCurrency(val value: String) : CurrencyConversionScreenEvent()
    data class MarkToFavoriteCurrency(val value: ExchangeRate) : CurrencyConversionScreenEvent()
    data class RemoveCurrencyFromSelected(val index: Int, val value: ExchangeRate) :
        CurrencyConversionScreenEvent()
    object ConvertCurrencyConversion : CurrencyConversionScreenEvent()
    object ForceSyncCurrencyConversion : CurrencyConversionScreenEvent() {

    }
}