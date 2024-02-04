package com.multiplatform.kmmcc.common.enums;

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
enum class WindowSize {
    COMPACT,
    MEDIUM,
    EXPANDED;

    // Factory method that creates an instance of the class based on window width
    companion object {
        fun basedOnCurrenDimension(windowWidth: Dp, windowHeight: Dp): WindowInfo {
            return when {
                windowWidth < 420.dp -> CompactWindow(
                    windowWidth.value,
                    windowWidth.value,
                    COMPACT
                )

                windowWidth < 580.dp -> MediumWindow(
                    windowWidth.value,
                    windowWidth.value,
                    COMPACT
                )

                else -> ExpandedWindow(
                    windowWidth.value,
                    windowWidth.value,
                    COMPACT
                )
            }
        }
    }
}

abstract class WindowInfo(
    open val windowWidth: Float,
    open val windowHeight: Float,
    open val windowSize: WindowSize
) {
}

class CompactWindow(
    override val windowWidth: Float,
    override val windowHeight: Float,
    override val windowSize: WindowSize
) : WindowInfo(windowWidth, windowHeight, windowSize)

data class MediumWindow(
    override val windowWidth: Float,
    override val windowHeight: Float,
    override val windowSize: WindowSize
) : WindowInfo(windowWidth, windowHeight, windowSize)

data class ExpandedWindow(
    override val windowWidth: Float,
    override val windowHeight: Float,
    override val windowSize: WindowSize
) : WindowInfo(windowWidth, windowHeight, windowSize)
