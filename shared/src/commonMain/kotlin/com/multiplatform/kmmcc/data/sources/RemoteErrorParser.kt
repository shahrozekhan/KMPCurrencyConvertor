package com.multiplatform.kmmcc.data.sources

import com.multiplatform.kmmcc.common.base.RemoteResource

class RemoteErrorParser {

    fun parseErrorInfo(errorResource: RemoteResource.ResourceError): String {
        return when (errorResource) {
            is RemoteResource.ResourceError.GenericError -> {
                errorResource.error?.let {
                    if (errorResource.error.error?.message?.isNotEmpty() == true) {
                        return errorResource.error.error.message
                    }
                    return errorResource.error.error?.info ?: ""
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