package com.multiplatform.kmmcc 

import io.ktor.client.engine.cio.*
import org.koin.dsl.module

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun platformKoinModule() = module { single { CIO.create() } }
