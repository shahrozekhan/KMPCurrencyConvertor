package com.multiplatform.kmmcc.domain.repository

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.data.dto.ExchangeRateDto
import database.ExchangeRateEntity

interface ExchangeRateRepository {

    suspend fun getExchangeRateFromRemote(): RemoteResource<List<ExchangeRateDto>>

    suspend fun insertExchangeRatesToDatabase(listOfExchangeRate: List<ExchangeRateDto>)

    suspend fun getExchangeRatesFromDatabase(): List<ExchangeRateEntity>

    suspend fun getExchangeRatesFromAssets(): Resource<List<ExchangeRateDto>>

    fun saveExchangeRatePreferences(key: String, exchangeRateStr: String)

    fun getExchangeRatePreferences(key: String)
}