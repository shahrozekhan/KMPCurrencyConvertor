package com.multiplatform.kmmcc.common.enums;

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class WindowSize {
    COMPACT,
    MEDIUM,
    EXPANDED;

    // Factory method that creates an instance of the class based on window width
    companion object {
        fun basedOnWidth(windowWidth: Dp): WindowSize {
            return when {
                windowWidth < 500.dp -> COMPACT
                windowWidth < 700.dp -> MEDIUM
                else -> EXPANDED
            }
        }
    }
}