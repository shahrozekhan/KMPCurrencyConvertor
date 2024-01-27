package com.multiplatform.kmmcc.presentation.components.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.multiplatform.kmmcc.common.Constants
import com.multiplatform.kmmcc.common.views.HeadingMedium
import com.multiplatform.kmmcc.common.views.OvershootInterpolator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreen() {
    val scale = remember {
        Animatable(0f)
    }

    val rotate = remember {
        Animatable(0f)
    }
    val scaleInterpolator = remember {
        OvershootInterpolator(2f)
    }
    val texScale = remember {
        Animatable(0.2f, 1f)
    }

    LaunchedEffect(key1 = true) {
        val dispatcher: CoroutineDispatcher = Dispatchers.Main
        withContext(dispatcher) {
            launch {
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = 2000,
                        easing = {
                            scaleInterpolator.getInterpolation(it)
                        }
                    )
                )
            }
            launch {
                rotate.animateTo(
                    targetValue = 180f,
                    animationSpec = tween(500, easing = FastOutLinearInEasing)
                )
            }
            launch {
                texScale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(400, easing = FastOutLinearInEasing)
                )
            }
            delay(Constants.SPLASH_DURATION)
            //TODO apply navigation.....
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource("splash_logo.xml"),
                contentDescription = "Logo",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(150.dp).scale(scale.value).rotate(rotate.value),
            )

            HeadingMedium("Currency Convertor", modifier = Modifier.alpha(texScale.value).graphicsLayer {
                scaleX = texScale.value
                scaleY = texScale.value
                transformOrigin = TransformOrigin.Center
            }, color = MaterialTheme.colorScheme.primary)
        }
    }
}