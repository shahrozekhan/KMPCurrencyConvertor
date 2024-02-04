package com.multiplatform.kmmcc

import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun databaseName(): String

expect fun platformKoinModule(): Module
