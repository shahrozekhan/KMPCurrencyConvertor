package com.multiplatform.kmmcc.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.multiplatform.kmmcc.common.theme.DarkColorScheme
import com.multiplatform.kmmcc.common.theme.LightColorScheme
import com.multiplatform.kmmcc.common.theme.typography
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

private val cache: MutableMap<String, Font> = mutableMapOf()

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font {
    return cache.getOrPut(res) {
        val byteArray = runBlocking { resource("font/$res.ttf").readBytes() }
        androidx.compose.ui.text.platform.Font(res, byteArray, weight, style)
    }
}

@Composable
actual fun CurrencyConvertorTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        content = content,
        typography = typography()
    )
}
