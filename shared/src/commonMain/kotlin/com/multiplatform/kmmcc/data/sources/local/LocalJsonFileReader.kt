package com.multiplatform.kmmcc.data.sources.local

import com.multiplatform.kmmcc.common.base.Resource
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

class LocalJsonFileReader {

    val parseException = LocalJsonExceptionParser()

    @OptIn(ExperimentalResourceApi::class)
    suspend fun loadJsonFile(fileName: String): String {
        val jsonStr = resource(fileName).readBytes().decodeToString()
        return jsonStr
    }

    inline fun <reified T> loadJsonFileFromResources(jsonString: String): Resource<T> {
        return try {
            val exchangeRateJson = Json.decodeFromString<T>(jsonString)
            Resource.Success(exchangeRateJson)
        } catch (e: Exception) {
            Resource.Error(parseException.logParseDisplayMessage(e))
        }
    }


}