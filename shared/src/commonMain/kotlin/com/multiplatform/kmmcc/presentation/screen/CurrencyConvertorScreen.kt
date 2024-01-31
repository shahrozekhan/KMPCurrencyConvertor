package com.multiplatform.kmmcc.presentation.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import cafe.adriel.voyager.core.screen.Screen
import com.multiplatform.kmmcc.common.enums.WindowSize
import com.multiplatform.kmmcc.presentation.components.conversion.CurrencyConversionScreen


class CurrencyConvertorScreen(private val windowSize: MutableState<WindowSize>) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        CurrencyConversionScreen(windowSize)
    }

}


