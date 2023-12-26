package com.multiplatform.kmmcc

import androidx.compose.ui.window.ComposeUIViewController
import com.multiplatform.kmmcc.di.injectKoin
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import platform.darwin.NSObject

fun MainViewController() = ComposeUIViewController {
    injectKoin(NSObject())

    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        isDarkTheme,
        false
    )

}
