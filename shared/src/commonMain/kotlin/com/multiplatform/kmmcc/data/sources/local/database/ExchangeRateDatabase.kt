package com.multiplatform.kmmcc.data.sources.local.database

import app.cash.sqldelight.db.SqlDriver

//Further read here if you want to implement different custom adapters for different datatypes.
//https://cashapp.github.io/sqldelight/2.0.0-alpha05/multiplatform_sqlite/types/
expect class ExchangeRateDriverFactory {
    fun createDriver(): SqlDriver
}