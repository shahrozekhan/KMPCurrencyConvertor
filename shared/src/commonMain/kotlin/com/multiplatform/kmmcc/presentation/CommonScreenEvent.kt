package com.multiplatform.kmmcc.presentation

import androidx.compose.material3.SnackbarDuration

sealed class CommonScreenEvent {
    data class ShowSnackbar(
        val message: String,
        val duration: SnackbarDuration = SnackbarDuration.Long
    ) : CommonScreenEvent()
}