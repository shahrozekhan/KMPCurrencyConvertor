package com.multiplatform.kmmcc

import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class CommonGreetingTest {

    @Test
    fun testExample() {
        val currentMoment = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
        val timeSaved =Clock.System.now().toLocalDateTime(TimeZone.UTC).date
//            Instant.fromEpochSeconds(1699819743L)
//                .toLocalDateTime(TimeZone.UTC).date
        println(1.3f.toBigDecimal().multiply("32.332".toBigDecimal().roundToDigitPosition(3,RoundingMode.ROUND_HALF_AWAY_FROM_ZERO)).toPlainString())

        assertEquals(currentMoment, timeSaved)
    }
}