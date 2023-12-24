package com.multiplatform.kmmcc.data.sources

import com.multiplatform.kmmcc.common.base.RemoteResource

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