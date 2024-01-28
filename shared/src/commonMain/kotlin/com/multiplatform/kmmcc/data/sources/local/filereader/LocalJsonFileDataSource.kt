package com.multiplatform.kmmcc.data.sources.local.filereader

import com.multiplatform.kmmcc.common.base.Resource
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.readResourceBytes

class LocalJsonFileDataSource {

    val parseException = LocalJsonExceptionParser()

    @OptIn( InternalResourceApi::class)
    suspend fun loadJsonFile(fileName: String): String {
        val jsonStr = readResourceBytes(fileName).decodeToString()
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