package com.multiplatform.kmmcc.presentation.components.conversionscreen

import com.multiplatform.kmmcc.domain.model.ExchangeRate


sealed class ExchangeRateScreenEvent {
    data class EnteredAmount(val value: String) : ExchangeRateScreenEvent()
    data class SelectFromCurrency(val value: String) : ExchangeRateScreenEvent()
    data class MarkToFavoriteCurrency(val value: ExchangeRate) : ExchangeRateScreenEvent()
    data class RemoveCurrencyFromSelected(val index: Int, val value: ExchangeRate) :
        ExchangeRateScreenEvent()
    object ConvertExchangeRate : ExchangeRateScreenEvent()
    object ForceSyncExchangeRate : ExchangeRateScreenEvent() {

    }
}