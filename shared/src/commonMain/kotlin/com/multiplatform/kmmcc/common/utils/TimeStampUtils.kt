package com.multiplatform.kmmcc.common.utils

import com.shahroze.currencyconvertorandroid.common.enums.TimeStampState
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

//import java.text.SimpleDateFormat
//import java.util.*

object TimeStampUtils {
    fun getTimeStampEnum(timeStamp: String): TimeStampState {
        val currentMoment = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
        return if (timeStamp.isEmpty()) {
            TimeStampState.NOT_EXIST
        } else if (currentMoment == localDate(timeStamp))
            TimeStampState.TODAY
        else
            TimeStampState.NOT_TODAY

    }

    private fun localDate(timeStamp: String) = Instant.fromEpochSeconds(timeStamp.toLong(), 0L)
        .toLocalDateTime(TimeZone.UTC).date

}