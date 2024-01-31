package com.multiplatform.kmmcc.data.sources.local.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.multiplatform.kmmcc.database.ExchangeRateDB
actual class ExchangeRateDriverFactory {
     actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:src/desktopMain/app.db",
                schema = ExchangeRateDB.Schema)
        return driver
    }
}