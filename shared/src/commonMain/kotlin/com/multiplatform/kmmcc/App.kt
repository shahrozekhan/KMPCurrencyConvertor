package com.multiplatform.kmmcc

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.multiplatform.kmmcc.common.enums.WindowInfo
import com.multiplatform.kmmcc.common.enums.WindowSize
import com.multiplatform.kmmcc.common.theme.CurrencyConvertorTheme
import com.multiplatform.kmmcc.presentation.components.conversion.CurrencyConversionScreen
import com.multiplatform.kmmcc.presentation.components.conversion.CurrencyConversionViewModel
import com.multiplatform.kmmcc.presentation.screen.SplashScreen
import org.koin.core.Koin
import org.koin.core.context.startKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    applicationWindowSize: MutableState<WindowInfo> = mutableStateOf(
        WindowSize.basedOnCurrenDimension(
            0.dp,
            0.dp
        )
    ),
    darkTheme: Boolean,
    dynamicColor: Boolean,
    koin: Koin
) {
    windowSize.value = applicationWindowSize.value
    koinG = koin
    CurrencyConvertorTheme(
        darkTheme = darkTheme, dynamicColor = dynamicColor
    ) {

        CurrencyConversionScreen(windowSize, koinG.get<CurrencyConversionViewModel>())
//        Navigator(
//            screen = SplashScreen()
//        ) { navigator ->
//            FadeTransition(navigator)
//        }

    }
}

var koinG: Koin = Koin()

val windowSize = mutableStateOf(
    WindowSize.basedOnCurrenDimension(
        0.dp,
        0.dp
    )
)