package com.multiplatform.kmmcc

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.multiplatform.kmmcc.common.theme.CurrencyConvertorTheme
import com.multiplatform.kmmcc.presentation.components.ExchangeRateScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    CurrencyConvertorTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        ExchangeRateScreen()
    }
}