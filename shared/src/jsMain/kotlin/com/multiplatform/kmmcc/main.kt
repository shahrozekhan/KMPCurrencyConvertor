package com.multiplatform.kmmcc

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import com.multiplatform.kmmcc.common.enums.WindowSize
import com.multiplatform.kmmcc.data.sources.local.database.ExchangeRateDriverFactory
import com.multiplatform.kmmcc.di.injectKoin
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady
import org.w3c.dom.events.Event

val koin = injectKoin(Any(), ExchangeRateDriverFactory().createDriver()).koin

val windowSizeMain = mutableStateOf(WindowSize.basedOnCurrenDimension(800.dp, 400.dp))

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow(canvasElementId = "ComposeTarget") {
//            window.addEventListener("resize", ::resize)
            windowSizeMain.value =
                WindowSize.basedOnCurrenDimension(window.outerWidth.dp, window.outerHeight.dp)
            App(
                windowSizeMain,
                darkTheme = false,
                dynamicColor = false,
                koin
            )
        }
    }
}

@JsName("resize")
fun resize(event: Event) {
    windowSizeMain.value =
        WindowSize.basedOnCurrenDimension(window.outerWidth.dp, window.outerHeight.dp)
}