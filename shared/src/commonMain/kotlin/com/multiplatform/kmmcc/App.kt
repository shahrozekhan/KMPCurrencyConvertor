package com.multiplatform.kmmcc

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.multiplatform.kmmcc.common.enums.WindowInfo
import com.multiplatform.kmmcc.common.enums.WindowSize
import com.multiplatform.kmmcc.common.theme.CurrencyConvertorTheme
import com.multiplatform.kmmcc.presentation.screen.SplashScreen

@Composable
fun App(
    applicationWindowSize: MutableState<WindowInfo> = mutableStateOf(
        WindowSize.basedOnCurrenDimension(
            0.dp,
            0.dp
        )
    ),
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    windowSize.value = applicationWindowSize.value
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

val windowSize = mutableStateOf(
    WindowSize.basedOnCurrenDimension(
        0.dp,
        0.dp
    )
)