package com.multiplatform.kmmcc.common.utils

val String.Companion.empty: String get() = ""

fun String.containsDigitsAndDecimalOnly(): Boolean {
    val check = Regex("[0-9]*(\\.[0-9]*)?")
    return check.matches(this)
}

fun Double.roundUp(place: Int): Double = this.roundUp(place)
//fun Double.roundUpFiveDecimal(): Double = String.format("%.5f", this).toDouble()