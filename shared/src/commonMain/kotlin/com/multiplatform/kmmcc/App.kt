package com.multiplatform.kmmcc

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.multiplatform.kmmcc.common.theme.CurrencyConvertorTheme
import com.multiplatform.kmmcc.presentation.screen.SplashScreen

@Composable
fun App(
    darkTheme: Boolean, dynamicColor: Boolean
) {
    CurrencyConvertorTheme(
        darkTheme = darkTheme, dynamicColor = dynamicColor
    ) {
        Navigator(
            screen = SplashScreen()
        ) { navigator ->
            FadeTransition(navigator)
        }

    }
}