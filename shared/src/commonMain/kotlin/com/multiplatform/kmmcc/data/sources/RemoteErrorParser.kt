package com.multiplatform.kmmcc.data.sources

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.common.dto.Error
import com.multiplatform.kmmcc.common.dto.ErrorResponse
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.utils.io.errors.*
import kotlinx.serialization.json.Json

class RemoteErrorParser {

    suspend fun parseError(throwable: Throwable): RemoteResource.ResourceError {
        return when (throwable) {
            is IOException -> RemoteResource.ResourceError.NetworkError(throwable = throwable)
            is ResponseException -> {
                val errorResponse = convertErrorBody(throwable)
                return RemoteResource.ResourceError.GenericError(errorResponse, throwable)
            }

            else -> {
                RemoteResource.ResourceError.GenericError(
                    ErrorResponse(
                        error = Error(
                            code = "0",
                            info = throwable.message ?: "",
                            message = ""
                        ), success = false
                    ), throwable
                )
            }
        }
    }

    private suspend fun convertErrorBody(throwable: ResponseException): ErrorResponse? {
        val json: String? = throwable.response.body()
        return try {
            Json.decodeFromString<ErrorResponse>(json ?: "")
        } catch (exception: Exception) {
            null
        }
    }

    fun parseErrorInfo(errorResource: RemoteResource.ResourceError): String {
        return when (errorResource) {
            is RemoteResource.ResourceError.GenericError -> {
                errorResource.error?.let {
                    if (errorResource.error.error.message.isNotEmpty()) {
                        return errorResource.error.error.message
                    }
                    return errorResource.error.error.info
                } ?: run {
                    return "Please try again later!"
                }
            }

            else -> {
                "Please check your internet connection!"
            }
        }
    }
}