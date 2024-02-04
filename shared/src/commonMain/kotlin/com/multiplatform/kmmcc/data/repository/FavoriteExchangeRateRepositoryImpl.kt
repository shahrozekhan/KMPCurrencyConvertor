package com.multiplatform.kmmcc.data.repository

import com.multiplatform.kmmcc.database.ApplicationDB
import com.multiplatform.kmmcc.domain.model.toExchangeRate
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import database.FromEntity
import database.ToEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FavoriteExchangeRateRepositoryImpl(
    private val sqlDatabase: ApplicationDB,
    private val defaultDispatcher: CoroutineDispatcher
) : FavoriteExchangeRateRepository {
    override suspend fun getToExchangeRates() = withContext(defaultDispatcher) {
        sqlDatabase.toentityQueries.getToExchangeRates().executeAsList()
            .map { it.toExchangeRate() }
    }

    override suspend fun getFromExchangeRates() = withContext(defaultDispatcher) {
        sqlDatabase.fromentityQueries.getFromExchangeRates().executeAsList()
            .map { it.toExchangeRate() }
    }

    override suspend fun markTOExchangeRates(exchangeRateEntity: ToEntity) =
        withContext(defaultDispatcher) {
            sqlDatabase.toentityQueries.updateAndInsertTOExchangeRate(exchangeRateEntity)
        }

    override suspend fun markFromExchangeRates(exchangeRateEntity: FromEntity) =
        withContext(defaultDispatcher) {
            sqlDatabase.fromentityQueries.updateAndInsertFROMExchangeRate(exchangeRateEntity)
        }

    override suspend fun clearFavorites() = withContext(defaultDispatcher) {
        sqlDatabase.fromentityQueries.delete()
        sqlDatabase.toentityQueries.delete()
    }

}