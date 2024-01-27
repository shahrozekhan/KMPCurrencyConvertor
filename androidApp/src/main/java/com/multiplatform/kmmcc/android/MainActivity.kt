package com.multiplatform.kmmcc.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.multiplatform.kmmcc.App
import com.multiplatform.kmmcc.presentation.components.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true
            )
        }
    }
}

@Composable
@Preview(locale = "en")
fun SplashScreenPreview(){
    SplashScreen()

}