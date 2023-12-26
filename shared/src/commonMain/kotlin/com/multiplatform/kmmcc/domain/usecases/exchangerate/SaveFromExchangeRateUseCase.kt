package com.multiplatform.kmmcc.domain.usecases.exchangerate

import com.multiplatform.kmmcc.domain.repository.ExchangeRateRepository

class SaveFromExchangeRateUseCase(private val exchangeRateRepo: ExchangeRateRepository) {
    operator fun invoke(exchangeRateStr: String) {
        exchangeRateRepo.SaveFromExchangeRate(exchangeRateStr)
    }
}