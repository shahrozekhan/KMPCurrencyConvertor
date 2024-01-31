package com.multiplatform.kmmcc.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.multiplatform.kmmcc.common.theme.LightColorScheme
import com.multiplatform.kmmcc.common.theme.typography

@Composable
actual fun CurrencyConvertorTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content,
        typography = typography()
    )
}
