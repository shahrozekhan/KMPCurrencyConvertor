package com.multiplatform.kmmcc

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        val currentMoment = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
        val timeSaved =Clock.System.now().toLocalDateTime(TimeZone.UTC).date
//            Instant.fromEpochSeconds(1699819743L)
//                .toLocalDateTime(TimeZone.UTC).date
        assertEquals(currentMoment, timeSaved)
    }
}