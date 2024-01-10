package com.multiplatform.kmmcc.data.sources.remote.ktor

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.common.dto.Error
import com.multiplatform.kmmcc.common.dto.ErrorResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*

/**
 * Safe request -> Exception Interceptor for Success and Error Callbacks.
 *
 * @param T
 * @param block
 * @receiver
 * @return
 */
suspend inline fun <reified T> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): RemoteResource<T> {
    return try {
        val response = request { block() }
        if (response.status.isSuccess()) {
            RemoteResource.Success(response.body())
        } else {
            val errorResponse = response.body() as ErrorResponse
            RemoteResource.ResourceError.GenericError(
                errorResponse.copy(httpStatus = response.status.value),
                Exception(response.status.description)
            )
        }
    } catch (error: Exception) {
        when (error) {
            is IOException -> RemoteResource.ResourceError.NetworkError(throwable = error)
            else -> {
                RemoteResource.ResourceError.GenericError(
                    ErrorResponse(
                        error = Error(
                            code = "0", info = error.message ?: "", message = ""
                        ), success = false
                    ), error
                )
            }
        }
    }
}

/*
suspend inline fun <reified T, reified E> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): ApiResponse<T, E> =
    try {
        val response = request { block() }
        ApiResponse.Success(response.body())
    } catch (e: ClientRequestException) {
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: ServerResponseException) {
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: IOException) {
        ApiResponse.Error.NetworkError
    } catch (e: SerializationException) {
        ApiResponse.Error.SerializationError
    }*/
