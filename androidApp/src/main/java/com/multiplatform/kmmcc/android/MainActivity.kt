package com.multiplatform.kmmcc.android

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.core.view.WindowCompat
import androidx.window.layout.WindowMetricsCalculator
import com.multiplatform.kmmcc.App
import com.multiplatform.kmmcc.common.enums.WindowInfo
import com.multiplatform.kmmcc.common.enums.CompactWindow
import com.multiplatform.kmmcc.common.enums.WindowSize
import com.multiplatform.kmmcc.data.sources.local.database.ExchangeRateDriverFactory
import com.multiplatform.kmmcc.di.injectKoin
import org.koin.compose.getKoin
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    val windowSizeState = mutableStateOf<WindowInfo>(CompactWindow(1f, 2f, WindowSize.COMPACT))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            windowSizeState.value = rememberWindowSize()
            App(
                applicationWindowSize = windowSizeState,
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true,
                getKoin()
            )
        }
    }
}

@Composable
private fun Activity.rememberWindowSize(): WindowInfo {
    val configuration = LocalConfiguration.current
    val windowMetrics = remember(configuration) {
        WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)
    }
    val windowDpSize = with(LocalDensity.current) {
        windowMetrics.bounds.toComposeRect().size.toDpSize()
    }
    return WindowSize.basedOnCurrenDimension(windowDpSize.width, windowDpSize.height)
}