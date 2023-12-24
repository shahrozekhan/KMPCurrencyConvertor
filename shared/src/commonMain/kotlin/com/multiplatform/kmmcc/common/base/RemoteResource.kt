package com.multiplatform.kmmcc.common.base

import com.multiplatform.kmmcc.common.dto.ErrorResponse


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