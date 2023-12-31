package com.multiplatform.kmmcc.data.repository

import com.multiplatform.kmmcc.database.ExchangeRateDB
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.domain.model.toExchangeRate
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import database.ExchangeRateEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FavoriteExchangeRateRepositoryImpl(
    private val sqlDatabase: ExchangeRateDB,
    private val defaultDispatcher: CoroutineDispatcher
) : FavoriteExchangeRateRepository {

    override suspend fun getFavoriteExchangeRates(): List<ExchangeRate> =
        withContext(defaultDispatcher) {
            sqlDatabase.exchangeratedbQueries.getFavoriteExchangeRates().executeAsList()
                .map { it.toExchangeRate() }
        }

    override suspend fun markExchangeRateFavorite(exchangeRateEntity: ExchangeRateEntity) =
        withContext(defaultDispatcher) {
            sqlDatabase.exchangeratedbQueries.updateAndInsertExchangeRate(exchangeRateEntity)
        }

}