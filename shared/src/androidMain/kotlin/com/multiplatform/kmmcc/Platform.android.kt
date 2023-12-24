package com.multiplatform.kmmcc

import io.ktor.client.engine.android.*
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun platformKoinModule() = module { single { Android.create() } }
