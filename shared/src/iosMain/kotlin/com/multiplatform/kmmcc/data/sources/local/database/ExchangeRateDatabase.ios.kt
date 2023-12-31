package com.multiplatform.kmmcc.data.sources.local.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.multiplatform.kmmcc.database.ExchangeRateDB
actual class ExchangeRateDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(ExchangeRateDB.Schema, "exchangeratedb.db")
    }
}