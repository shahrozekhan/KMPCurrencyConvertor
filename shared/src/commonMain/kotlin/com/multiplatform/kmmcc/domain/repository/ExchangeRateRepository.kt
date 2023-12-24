package com.multiplatform.kmmcc.domain.repository

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.data.dto.ExchangeRateDto

interface ExchangeRateRepository {

    suspend fun getExchangeRateFromRemote(): RemoteResource<List<ExchangeRateDto>>

    suspend fun insertExchangeRatesToDatabase(listOfExchangeRate: List<ExchangeRateDto>)

    suspend fun getExchangeRatesFromDatabase(): List<ExchangeRateDto>

    suspend fun getExchangeRatesFromAssets(): Resource<List<ExchangeRateDto>>
}