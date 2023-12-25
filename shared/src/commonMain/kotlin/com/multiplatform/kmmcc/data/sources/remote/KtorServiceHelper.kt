package com.multiplatform.kmmcc.data.sources.remote

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.data.sources.RemoteErrorParser

class KtorServiceHelper(
    private val errorParser: RemoteErrorParser,
) {
    suspend fun <T> call(
        apiCall: suspend () -> T
    ): RemoteResource<T> {
        return try {
            RemoteResource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            errorParser.parseError(throwable = throwable)
        }
    }
}