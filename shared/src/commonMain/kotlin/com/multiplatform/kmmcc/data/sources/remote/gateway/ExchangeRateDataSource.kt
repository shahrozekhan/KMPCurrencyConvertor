package com.multiplatform.kmmcc.data.sources.remote.gateway

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.data.dto.ExchangeRateResponseDto
import com.multiplatform.kmmcc.data.sources.remote.gateway.ExchangeRateDataSource.ExchangeRateRoutes.GET_EXCHANGERATE
import com.multiplatform.kmmcc.data.sources.remote.gateway.ExchangeRateDataSource.ExchangeRateRoutes.GET_SYMBOLS
import com.multiplatform.kmmcc.data.sources.remote.ktor.safeRequest
import io.ktor.client.*
import io.ktor.http.*

class ExchangeRateDataSource(private val httpClient: HttpClient) {
    private object ExchangeRateRoutes {
        const val GET_EXCHANGERATE = "/v1/latest"
        const val GET_SYMBOLS = "/v1/symbols"
    }

    suspend fun getExchangeRate(): RemoteResource<ExchangeRateResponseDto> =
        httpClient.safeRequest {
            this.url {
                method = HttpMethod.Get
                path(GET_EXCHANGERATE)
            }
        }

    suspend fun getSymbols(): RemoteResource<ExchangeRateResponseDto> =
        httpClient.safeRequest {
            this.url {
                method = HttpMethod.Get
                path(GET_SYMBOLS)
            }
        }

}