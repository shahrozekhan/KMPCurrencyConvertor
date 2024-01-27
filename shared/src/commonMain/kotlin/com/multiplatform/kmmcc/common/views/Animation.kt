package com.multiplatform.kmmcc.common.views

import kotlin.math.pow

class OvershootInterpolator(private val tension: Float = 2.0f) :
    Interpolator {
    override fun getInterpolation(input: Float): Float {
        // Apply the overshoot interpolation formula
        val tension = if (tension == 0f) 1.70158f else tension
        return (input - 1).pow(2f) * ((tension + 1) * input - tension) + 1
    }

}

internal fun interface Interpolator {
    fun getInterpolation(input: Float): Float
}