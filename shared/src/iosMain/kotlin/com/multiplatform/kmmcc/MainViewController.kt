package com.multiplatform.kmmcc

import androidx.compose.ui.window.ComposeUIViewController
import com.multiplatform.kmmcc.data.sources.local.database.ExchangeRateDriverFactory
import com.multiplatform.kmmcc.di.injectKoin
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import platform.darwin.NSObject

fun MainViewController() = ComposeUIViewController {
    injectKoin(NSObject(), ExchangeRateDriverFactory().createDriver())

    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false
    )

}
