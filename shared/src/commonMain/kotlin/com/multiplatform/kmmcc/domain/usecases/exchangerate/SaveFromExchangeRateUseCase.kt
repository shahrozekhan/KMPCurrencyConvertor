package com.multiplatform.kmmcc.domain.usecases.exchangerate

import com.multiplatform.kmmcc.domain.repository.ExchangeRateRepository

class SavePersistedExchangeRateUseCase(private val exchangeRateRepo: ExchangeRateRepository) {
    operator fun invoke(key: String, exchangeRateStr: String) {
        exchangeRateRepo.saveExchangeRatePreferences(key, exchangeRateStr)
    }
}

class GetPersistedExchangeRateUseCase(private val exchangeRateRepo: ExchangeRateRepository) {
    operator fun invoke(key: String) {
        exchangeRateRepo.getExchangeRatePreferences(key)
    }
}

data class PersistExchangeRate(
    val saveFromExchangeRateUseCase: SavePersistedExchangeRateUseCase,
    val getPersistedExchangeRateUseCase: GetPersistedExchangeRateUseCase
)