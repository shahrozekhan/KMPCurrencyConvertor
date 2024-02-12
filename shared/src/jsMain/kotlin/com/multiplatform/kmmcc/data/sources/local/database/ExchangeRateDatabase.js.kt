package com.multiplatform.kmmcc.data.sources.local.database

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import com.multiplatform.kmmcc.database.ApplicationDB
import org.w3c.dom.Worker

//Further read here if you want to implement different custom adapters for different datatypes.
//https://cashapp.github.io/sqldelight/2.0.0-alpha05/multiplatform_sqlite/types/
actual class ExchangeRateDriverFactory {
    actual fun createDriver(): SqlDriver {
        val workerScriptUrl =
            Worker(
                js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
            )
        val driver = WebWorkerDriver(workerScriptUrl).also { ApplicationDB.Schema.create(it) }
        return driver
    }
}

suspend fun createDriver(): SqlDriver {
    val workerScriptUrl =
        Worker(
            js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
        )
    val driver = WebWorkerDriver(workerScriptUrl).also { ApplicationDB.Schema.awaitCreate(it) }
    return driver
}