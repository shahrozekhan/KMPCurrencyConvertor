package com.multiplatform.kmmcc.domain.repository

import com.multiplatform.kmmcc.data.dto.ExchangeRateDto


interface FavoriteExchangeRateRepository {

    suspend fun getFavoriteExchangeRates(): List<ExchangeRateDto>

    suspend fun markExchangeRateFavorite(exchangeRateDto: ExchangeRateDto)


}