package com.multiplatform.kmmcc.domain.usecases.favorite

import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class MarkExchangeRateToFavoriteUseCase(
    private val favoriteExchangeRateRepository: FavoriteExchangeRateRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        exchangeRate: ExchangeRate,
        listOfFavorite: List<ExchangeRate>?,
        isSelected: Boolean = true
    ): List<ExchangeRate> =
        withContext(dispatcher) {
            val mutableListOfFavorites = listOfFavorite?.toMutableList()
            if (isSelected) {
                if (mutableListOfFavorites?.filter { exchangeRate == it }
                        .isNullOrEmpty()) {
                    mutableListOfFavorites?.add(exchangeRate)
                }
            } else {
                val exchangeRateObj = mutableListOfFavorites?.find { exchangeRate == it }
                if (exchangeRateObj != null) {
                    mutableListOfFavorites.remove(exchangeRate)
                }
            }
//            val exchangeRateDto = exchangeRate.toExchangeRateDto()
//            exchangeRateDto.selected = isSelected
//            favoriteExchangeRateRepository.markExchangeRateFavorite(exchangeRateDto = exchangeRateDto)
            mutableListOfFavorites?.toList() ?: listOf()
        }
}