package com.multiplatform.kmmcc

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.multiplatform.kmmcc.common.enums.WindowSize
import com.multiplatform.kmmcc.common.theme.CurrencyConvertorTheme
import com.multiplatform.kmmcc.presentation.screen.SplashScreen

@Composable
fun App(
    applicationWindowSize: MutableState<WindowSize> = mutableStateOf(WindowSize.COMPACT),
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    windowSize.value = applicationWindowSize.value
    CurrencyConvertorTheme(
        darkTheme = darkTheme, dynamicColor = dynamicColor
    ) {
        Navigator(
            screen = SplashScreen(windowSize)
        ) { navigator ->
            FadeTransition(navigator)
        }

    }
}

val windowSize = mutableStateOf(WindowSize.COMPACT)