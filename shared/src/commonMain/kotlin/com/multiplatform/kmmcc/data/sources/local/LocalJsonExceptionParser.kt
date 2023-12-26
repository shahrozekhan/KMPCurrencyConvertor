package com.multiplatform.kmmcc.data.sources.local

import io.ktor.serialization.*

class LocalJsonExceptionParser{
    fun logParseDisplayMessage(exception: Exception): String {
        exception.printStackTrace()
        return when (exception) {
            is JsonConvertException -> {
                "Uable to parse"
            }

            else -> {
                "An expected exception occurred!"
            }
        }
    }

}