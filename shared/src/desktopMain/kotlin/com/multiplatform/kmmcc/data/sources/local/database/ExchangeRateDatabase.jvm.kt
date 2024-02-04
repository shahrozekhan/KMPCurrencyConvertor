package com.multiplatform.kmmcc.data.sources.local.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.multiplatform.kmmcc.database.ApplicationDB
import com.multiplatform.kmmcc.databaseName

actual class ExchangeRateDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(
            "jdbc:sqlite:src/desktopMain/${databaseName()}",
            schema = ApplicationDB.Schema
        )
        try {
            ApplicationDB.Schema.create(driver)
        } catch (e: Exception) {

        }
        return driver
    }
}