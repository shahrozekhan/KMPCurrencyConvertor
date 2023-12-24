package com.multiplatform.kmmcc.common.base

import com.multiplatform.kmmcc.common.utils.empty


sealed class Resource<out T>(val data: T? = null, val message: String) {
    class Success<T>(data: T) : Resource<T>(data, String.empty)
    class Error<T>(message: String? = "", data: T? = null) : Resource<T>(data, message ?: "")
    class Loading<T>(data: T? = null) : Resource<T>(data, String.empty)
}