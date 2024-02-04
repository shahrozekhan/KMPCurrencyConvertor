package com.multiplatform.kmmcc.domain.usecases.favorite

import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.domain.model.toFromEntity
import com.multiplatform.kmmcc.domain.model.toToEntity
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MarkExchangeRateToFavoriteUseCase(
    private val favoriteExchangeRateRepository: FavoriteExchangeRateRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        listOfTo: List<ExchangeRate>?,
        listOfFrom: List<ExchangeRate>?
    ) =
        withContext(dispatcher) {
            favoriteExchangeRateRepository.clearFavorites()
            listOfTo?.forEach {
                favoriteExchangeRateRepository.markTOExchangeRates(exchangeRateEntity = it.toToEntity())
            }
            listOfFrom?.forEach {
                favoriteExchangeRateRepository.markFromExchangeRates(exchangeRateEntity = it.toFromEntity())
            }
        }
}