package com.multiplatform.kmmcc.di

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

object ExchangeRateHttpConstants {
    const val baseUrl = "http://api.exchangeratesapi.io"
}

val httpClientModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)

            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}