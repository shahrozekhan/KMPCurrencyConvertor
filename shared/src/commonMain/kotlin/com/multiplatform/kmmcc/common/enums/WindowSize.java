package com.multiplatform.kmmcc.common.enums;

enum class WindowSize {
  COMPACT,
  MEDIUM,
  EXPANDED;

  // Factory method that creates an instance of the class based on window width
    companion object {
        fun basedOnWidth(windowWidth: Dp): WindowSize {
            return when {
                windowWidth < 600.dp -> COMPACT
                windowWidth < 840.dp -> MEDIUM
                else -> EXPANDED
            }
        }
    }
}