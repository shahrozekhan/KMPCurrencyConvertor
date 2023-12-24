package com.multiplatform.kmmcc

import io.ktor.client.engine.darwin.*
import org.koin.dsl.module
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun platformKoinModule() = module { single { Darwin.create() } }
