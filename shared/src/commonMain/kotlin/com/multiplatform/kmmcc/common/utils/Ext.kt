package com.multiplatform.kmmcc.common.utils

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.RoundingMode

val String.Companion.empty: String get() = ""

fun String.containsDigitsAndDecimalOnly(): Boolean {
    val check = Regex("[0-9]*(\\.[0-9]*)?")
    return check.matches(this)
}

fun BigDecimal.roundUp(place: Int): BigDecimal =
    this.roundToDigitPosition(place.toLong(), RoundingMode.ROUND_HALF_AWAY_FROM_ZERO)

//fun Double.roundUpFiveDecimal(): Double = String.format("%.5f", this).toDouble()