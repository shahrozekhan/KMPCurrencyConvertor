package com.multiplatform.kmmcc.data.sources.local.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.multiplatform.kmmcc.database.ApplicationDB
import com.multiplatform.kmmcc.databaseName

actual class ExchangeRateDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = ApplicationDB.Schema, name = databaseName())
    }
}