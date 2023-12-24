package com.multiplatform.kmmcc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.multiplatform.kmmcc.common.theme.CurrencyConvertorTheme
import com.multiplatform.kmmcc.di.ApplicationKoinComponentModules
import com.multiplatform.kmmcc.di.appModules
import com.multiplatform.kmmcc.presentation.components.ExchangeRateScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext

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