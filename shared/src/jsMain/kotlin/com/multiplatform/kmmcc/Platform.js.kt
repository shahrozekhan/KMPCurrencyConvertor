package com.multiplatform.kmmcc

import io.ktor.client.engine.js.Js
import org.koin.dsl.module

class JsSPlatform : Platform {
    override val name: String = ""
}

actual fun getPlatform(): Platform = JsSPlatform()

actual fun platformKoinModule() = module { single { Js.create() } }
actual fun databaseName(): String = "web_app_db.db"