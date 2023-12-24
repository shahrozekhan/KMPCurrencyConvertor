package com.multiplatform.kmmcc.data.repository

import com.multiplatform.kmmcc.data.dto.ExchangeRateDto
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import kotlinx.coroutines.CoroutineDispatcher

class FavoriteExchangeRateRepositoryImpl
    (
//    private val exchangeRateDao: ExchangeRateDao,
    private val defaultDispatcher: CoroutineDispatcher
) : FavoriteExchangeRateRepository {

    //    override suspend fun getFavoriteExchangeRates(): List<ExchangeRateDto> =
//        withContext(defaultDispatcher) {
//            exchangeRateDao.getFavoriteExchangeRates()
//        }
//
//    override suspend fun markExchangeRateFavorite(exchangeRateDto: ExchangeRateDto) =
//        withContext(defaultDispatcher) {
//            exchangeRateDao.update(t = exchangeRateDto)
//        }
    override suspend fun getFavoriteExchangeRates(): List<ExchangeRateDto> {
        TODO("Not yet implemented")
    }

    override suspend fun markExchangeRateFavorite(exchangeRateDto: ExchangeRateDto) {
        TODO("Not yet implemented")
    }

}