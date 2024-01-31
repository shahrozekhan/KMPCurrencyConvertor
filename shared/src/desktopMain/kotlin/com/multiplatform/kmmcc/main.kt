import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.multiplatform.kmmcc.App
import com.multiplatform.kmmcc.common.enums.WindowSize
import com.multiplatform.kmmcc.data.sources.local.database.ExchangeRateDriverFactory
import com.multiplatform.kmmcc.di.injectKoin
import java.util.Properties

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(width = 400.dp, height = 800.dp))
    val windowSize = mutableStateOf(WindowSize.COMPACT)
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "DesktopVersion"
    ) {
        LaunchedEffect(Unit) {
            injectKoin(Properties(), ExchangeRateDriverFactory().createDriver())
        }
        windowSize.value = WindowSize.basedOnWidth(windowState.size.width)
        App(
            applicationWindowSize = windowSize,
            darkTheme = false,
            dynamicColor = false
        )
    }
}