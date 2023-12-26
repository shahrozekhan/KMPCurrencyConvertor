package com.multiplatform.kmmcc.data.sources.remote.gateway

import com.multiplatform.kmmcc.data.dto.ExchangeRateResponseDto
import com.multiplatform.kmmcc.di.ExchangeRateHttpConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ExchangeRateGateway(private val httpClient: HttpClient) {
    private object ExchangeRateRoutes {
        const val GET_EXCHANGERATE = "/v1/latest"
        const val GET_SYMBOLS = "/v1/symbols.json"
    }

    suspend fun getExchangeRate(): ExchangeRateResponseDto {
        return httpClient.get { url(ExchangeRateHttpConstants.baseUrl + ExchangeRateRoutes.GET_EXCHANGERATE) }
            .body()
    }

    suspend fun getSymbols(): ExchangeRateResponseDto {
        return httpClient.get { url(ExchangeRateHttpConstants.baseUrl + ExchangeRateRoutes.GET_SYMBOLS) }
            .body()
    }

}