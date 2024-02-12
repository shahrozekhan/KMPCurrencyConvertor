import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.multiplatform.kmmcc.App
import com.multiplatform.kmmcc.common.enums.WindowInfo
import com.multiplatform.kmmcc.common.enums.WindowSize
import com.multiplatform.kmmcc.data.sources.local.database.ExchangeRateDriverFactory
import com.multiplatform.kmmcc.di.injectKoin
import java.util.Properties

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(width = 400.dp, height = 800.dp))
    val windowSize = mutableStateOf<WindowInfo>(WindowSize.basedOnCurrenDimension(400.dp,800.dp))
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "DesktopVersion"
    ) {
        windowSize.value =
            WindowSize.basedOnCurrenDimension(windowState.size.width, windowState.size.height)
        App(
            applicationWindowSize = windowSize,
            darkTheme = false,
            dynamicColor = false,
            injectKoin(Properties(), ExchangeRateDriverFactory().createDriver()).koin
        )
    }
}