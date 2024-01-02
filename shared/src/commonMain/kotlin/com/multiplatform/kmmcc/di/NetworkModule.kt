package com.multiplatform.kmmcc.di

import com.multiplatform.kmmcc.common.Constants.ExhangeRateParams.ACCESS_KEY
import com.multiplatform.kmmcc.common.enums.HttpMethodType
import com.multiplatform.kmmcc.di.ExchangeRateHttpConstants.exchangeRateApiKey
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

object ExchangeRateHttpConstants {
    const val baseUrl = "http://api.exchangeratesapi.io"
    const val exchangeRateApiKey = "4355559f6761df5c780bb287da4a93e5"
}


val httpClientModule = module {
    single {
        //get function takes the Platform Specific engine
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(
                    json = createJson(),
                    contentType = ContentType.Any
                )

            }
            install(DefaultRequest){

            }
            defaultRequest {

            }

            install(Logging) {
                level = LogLevel.ALL
            }
        }
        httpClient.plugin(HttpSend).intercept(exchangeRateInterceptor)
        httpClient
    }
}

fun createJson() = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    prettyPrint = true
    coerceInputValues = true
}

val exchangeRateInterceptor: suspend Sender.(HttpRequestBuilder) -> HttpClientCall = { request ->
    request {
        when (this.method.value) {
            HttpMethodType.GET.type -> {
                parameters{
                        parameter(ACCESS_KEY, exchangeRateApiKey)
                }
            }
        }
    }
    execute(request)
}