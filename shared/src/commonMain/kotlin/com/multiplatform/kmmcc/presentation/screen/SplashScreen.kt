package com.multiplatform.kmmcc.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import cafe.adriel.voyager.core.screen.Screen
import com.multiplatform.kmmcc.common.enums.WindowSize
import com.multiplatform.kmmcc.presentation.components.spash.Splash

class SplashScreen(private val windowSize: MutableState<WindowSize>) : Screen {
    @Composable
    override fun Content() {
        Splash(windowSize)
    }

}