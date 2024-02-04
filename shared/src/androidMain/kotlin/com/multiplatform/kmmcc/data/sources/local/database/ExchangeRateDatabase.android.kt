package com.multiplatform.kmmcc.data.sources.local.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.multiplatform.kmmcc.database.ApplicationDB
import com.multiplatform.kmmcc.databaseName

actual class ExchangeRateDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(ApplicationDB.Schema, context, databaseName())
    }

}