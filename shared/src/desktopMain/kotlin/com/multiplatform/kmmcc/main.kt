import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.multiplatform.kmmcc.App
import com.multiplatform.kmmcc.data.sources.local.database.ExchangeRateDriverFactory
import com.multiplatform.kmmcc.di.injectKoin
import java.util.*

val properties = Properties()

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
           state = rememberWindowState(width = 800.dp, height = 600.dp),
           title = "DesktopVersion"
    ) {
        injectKoin(Properties(), ExchangeRateDriverFactory().createDriver())
        App(false, false)
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App(false, false)
}