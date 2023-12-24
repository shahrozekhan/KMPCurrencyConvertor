package com.multiplatform.kmmcc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform