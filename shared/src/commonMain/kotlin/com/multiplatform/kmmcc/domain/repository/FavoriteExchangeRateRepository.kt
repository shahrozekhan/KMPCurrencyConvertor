package com.multiplatform.kmmcc.domain.repository

import com.multiplatform.kmmcc.domain.model.ExchangeRate
import database.ExchangeRateEntity


interface FavoriteExchangeRateRepository {

    suspend fun getFavoriteExchangeRates(): List<ExchangeRate>

    suspend fun markExchangeRateFavorite(exchangeRateEntity: ExchangeRateEntity)


}