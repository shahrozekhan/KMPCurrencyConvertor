import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
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

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "DesktopVersion"
    ) {
        LaunchedEffect(Unit) {
            injectKoin(Properties(), ExchangeRateDriverFactory().createDriver())
        }
        App(
            windowSize = WindowSize.basedOnWidth(windowState.size.width),
            darkTheme = false,
            dynamicColor = false
        )
    }
}