package com.multiplatform.kmmcc.common.base

import com.multiplatform.kmmcc.common.dto.ErrorResponse
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import kotlinx.serialization.SerializationException


/**
 *
 */
sealed class RemoteResource<out T> {
    data class Success<out T>(val data: T) : RemoteResource<T>()
    sealed class ResourceError(open val throwable: Throwable) : RemoteResource<Nothing>() {
        data class GenericError(
            val error: ErrorResponse? = null,
            val exception: Throwable
        ) : ResourceError(exception)

        data class NetworkError(override val throwable: Throwable) : ResourceError(throwable)
    }
}

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.body()
    } catch (e: SerializationException) {
        null
    }

/*
sealed class ApiResponse<out T, out E> {
    data class Success<T>(val body: T) : ApiResponse<T, Nothing>()
    sealed class Error<E> : ApiResponse<Nothing, E>() {
        data class HttpError<E>(val code: Int, val errorBody: E?) : Error<E>()
        object NetworkError : Error<Nothing>()
        object SerializationError : Error<Nothing>()
    }
}*/
