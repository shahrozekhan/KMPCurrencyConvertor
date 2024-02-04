package com.multiplatform.kmmcc.domain.repository

import com.multiplatform.kmmcc.domain.model.ExchangeRate
import database.ExchangeRateEntity
import database.FromEntity
import database.ToEntity


interface FavoriteExchangeRateRepository {

    suspend fun getToExchangeRates(): List<ExchangeRate>

    suspend fun getFromExchangeRates(): List<ExchangeRate>

    //This abstraction provides user selected "To" currencies.
    suspend fun markTOExchangeRates(exchangeRateEntity: ToEntity)
    //This abstraction provides user selected from currencies.
    suspend fun markFromExchangeRates(exchangeRateEntity: FromEntity)

    suspend fun clearFavorites()

}