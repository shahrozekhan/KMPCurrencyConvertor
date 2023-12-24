package com.multiplatform.kmmcc.presentation

import androidx.compose.material3.SnackbarDuration

sealed class CommonUIEvent {
    data class ShowSnackbar(
        val message: String,
        val duration: SnackbarDuration = SnackbarDuration.Long
    ) : CommonUIEvent()
}