package com.multiplatform.kmmcc.data.sources.remote.gateway

import com.multiplatform.kmmcc.common.Constants
import com.multiplatform.kmmcc.data.dto.ExchangeRateResponseDto
import com.multiplatform.kmmcc.data.sources.remote.gateway.ExchangeRateGateway.ExchangeRateRoutes.GET_EXCHANGERATE
import com.multiplatform.kmmcc.data.sources.remote.gateway.ExchangeRateGateway.ExchangeRateRoutes.GET_SYMBOLS
import com.multiplatform.kmmcc.di.ExchangeRateHttpConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ExchangeRateGateway(private val httpClient: HttpClient) {
    private object ExchangeRateRoutes {
        const val GET_EXCHANGERATE = "/v1/latest"
        const val GET_SYMBOLS = "/v1/symbol"
    }

    suspend fun getExchangeRate(): ExchangeRateResponseDto {
        val response: HttpResponse = httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "jsonplaceholder.typicode.com"
                path("/posts")
                parameters.append(
                    Constants.ExhangeRateParams.ACCESS_KEY,
                    ExchangeRateHttpConstants.exchangeRateApiKey
                )
//            ExchangeRateHttpConstants.baseUrl + ExchangeRateRoutes.GET_EXCHANGERATE
            }
        }
        response.status.isSuccess()
        return response
            .body()
    }

    suspend fun getSymbols(): ExchangeRateResponseDto {
//        return
        val response: HttpResponse = httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "jsonplaceholder.typicode.com"
                path("/posts")
//                parameters.append(
//                    Constants.ExhangeRateParams.ACCESS_KEY,
//                    ExchangeRateHttpConstants.exchangeRateApiKey
//                )
//            ExchangeRateHttpConstants.baseUrl + ExchangeRateRoutes.GET_EXCHANGERATE
            }
        }
        response.status.isSuccess()
        return response
            .body()
        /*httpClient.get {
            url {
                protocol = URLProtocol.HTTP
                host = "api.exchangeratesapi.io"
                path(GET_SYMBOLS)
                parameters.append(
                    Constants.ExhangeRateParams.ACCESS_KEY,
                    ExchangeRateHttpConstants.exchangeRateApiKey
                )
//                ExchangeRateHttpConstants.baseUrl + ExchangeRateRoutes.GET_SYMBOLS
            }
        }
            .body()*/
    }

}