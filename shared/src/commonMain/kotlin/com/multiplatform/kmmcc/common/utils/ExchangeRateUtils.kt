package com.multiplatform.kmmcc.common.utils

import com.multiplatform.kmmcc.data.dto.ExchangeRateDto

/**
 * Build exchange rate dto list sorted by currency
 *
 * @param rates
 * @param symbols
 * @return
 */
fun buildExchangeRateDtoListSortedByCurrency(
    rates: HashMap<String, Double>?,
    symbols: HashMap<String, String>?
): List<ExchangeRateDto> = buildList {
    rates?.onEach { exchangeRateDto ->
        symbols?.get(exchangeRateDto.key)?.let {
            add(
                ExchangeRateDto(
                    currency = exchangeRateDto.key,
                    currencyName = it,
                    rate = exchangeRateDto.value,
                    selected = false
                )
            )
        }
    }
}

fun buildExchangeRateDtoListSortedByCurrency(
    rates: HashMap<String, Double>?,
): List<ExchangeRateDto> = buildList {
    rates?.onEach { exchangeRateDto ->
        add(
            ExchangeRateDto(
                currency = exchangeRateDto.key,
                currencyName = "",
                rate = exchangeRateDto.value,
                selected = false
            )
        )
    }
}