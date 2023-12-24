package com.multiplatform.kmmcc.common.utils

import com.shahroze.currencyconvertorandroid.common.enums.TimeStampState
//import java.text.SimpleDateFormat
//import java.util.*

object TimeStampUtils {
    const val timeFormat = "yyyy-MM-dd"
    fun getTimeStampEnum(timeStamp: String): TimeStampState {

//        val format = SimpleDateFormat(timeFormat)
//        val today = Date()
        return if (timeStamp.isEmpty()) {
            TimeStampState.NOT_EXIST
        }
//        } else if (format.format(Date(timeStamp.toLong() * 1000)) == format.format(today))
//            TimeStampState.TODAY
        else
            TimeStampState.NOT_TODAY

    }

}