import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.multiplatform.kmmcc.App
import com.multiplatform.kmmcc.data.sources.local.database.ExchangeRateDriverFactory
import com.multiplatform.kmmcc.di.injectKoin
import java.util.*

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = 400.dp, height = 800.dp),
        title = "DesktopVersion"
    ) {
        injectKoin(Properties(), ExchangeRateDriverFactory().createDriver())
        App(false, false)
    }
}