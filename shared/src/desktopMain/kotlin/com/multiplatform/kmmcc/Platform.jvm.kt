package com.multiplatform.kmmcc 

import com.multiplatform.kmmcc.*
import io.ktor.client.engine.java.*
import org.koin.dsl.module

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun platformKoinModule() = module { single { Java.create() } }
