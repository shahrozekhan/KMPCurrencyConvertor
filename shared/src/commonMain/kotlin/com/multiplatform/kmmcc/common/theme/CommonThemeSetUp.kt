package com.multiplatform.kmmcc.common.theme

import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun typography(): Typography {
    val convergenceNormal = FontFamily(
        font(
            "convergence", "convergence_normal", FontWeight.Normal, FontStyle.Normal
        )
    )

    return Typography(
        displayLarge = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 57.sp,
            lineHeight = 64.sp,
        ),
        displayMedium = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
            lineHeight = 52.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 44.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 36.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            lineHeight = 16.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = convergenceNormal,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        )
    )
}
val Black = Color(0xff000000)

// LIGHT
val GreenPrimaryLight = Color(0xff006e26)
val OnGreenLight = Color(0xffffffff)
val GreenContainerLight = Color(0xff6cff82)
val OnGreenContainerLight = Color(0xff002106)

val GreenSecondaryLight = Color(0xff526350)
val OnGreenSecondaryLight = OnGreenLight
val GreenSecondaryContainerLight = Color(0xffd4e8d0)
val OnGreenSecondaryContainerLight = Color(0xff101f10)

val GreenTertiaryLight = Color(0xff39656b)
val OnGreenTertiaryLight = OnGreenLight
val GreenTertiaryContainerLight = Color(0xffbcebf2)
val OnGreenTertiaryContainerLight = Color(0xff001f23)

val ErrorLight = Color(0xffba1a1a)
val OnErrorLight = Color(0xffffffff)
val ErrorContainerLight = Color(0xffffdad6)
val OnErrorContainerLight = Color(0xff410002)

val BackgroundLight = Color(0xfffcfdf7)
val OnBackgroundLight = Color(0xff1a1c19)
val SurfaceLight = BackgroundLight
val OnSurfaceLight = OnBackgroundLight
val SurfaceVariantLight = Color(0xffdee5d9)
val OnSurfaceVariantLight = Color(0xff424940)

val OutlineLight = Color(0xff72796f)

// DARK
val GreenPrimaryDark = Color(0xff00e559)
val OnGreenDark = Color(0xff003910)
val GreenContainerDark = Color(0xff00531b)
val OnGreenContainerDark = Color(0xff6cff82)

val GreenSecondaryDark = Color(0xffb9ccb5)
val OnGreenSecondaryDark = OnGreenDark
val GreenSecondaryContainerDark = Color(0xff3a4b39)
val OnGreenSecondaryContainerDark = Color(0xffd4e8d0)

val GreenTertiaryDark = Color(0xffa1ced5)
val OnGreenTertiaryDark = Color(0xff00363c)
val GreenTertiaryContainerDark = Color(0xff1f4d53)
val OnGreenTertiaryContainerDark = Color(0xffbcebf2)

val ErrorDark = Color(0xffffb4ab)
val OnErrorDark = Color(0xff690005)
val ErrorContainerDark = Color(0xff93000a)
val OnErrorContainerDark = Color(0xffffdad6)

val BackgroundDark = Color(0xff1a1c19)
val OnBackgroundDark = Color(0xffe2e3dd)
val SurfaceDark = BackgroundDark
val OnSurfaceDark = OnBackgroundDark
val SurfaceVariantDark = Color(0xff424940)
val OnSurfaceVariantDark = Color(0xffc2c9bd)

val OutlineDark = Color(0xff72796f)

val DarkColorScheme = darkColorScheme(
    primary = GreenPrimaryDark,
    secondary = GreenSecondaryDark,
    tertiary = GreenTertiaryDark,
    onPrimary = OnGreenDark,
    primaryContainer = GreenContainerDark,
    onPrimaryContainer = OnGreenContainerDark,
    onSecondary = OnGreenSecondaryDark,
    secondaryContainer = GreenSecondaryContainerDark,
    onSecondaryContainer = OnGreenSecondaryContainerDark,
    onTertiary = OnGreenTertiaryDark,
    onTertiaryContainer = OnGreenTertiaryContainerDark,
    tertiaryContainer = GreenTertiaryContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    outline = OutlineDark,
)

val LightColorScheme = lightColorScheme(
    primary = GreenPrimaryLight,
    secondary = GreenSecondaryLight,
    tertiary = GreenTertiaryLight,
    onPrimary = OnGreenLight,
    primaryContainer = GreenContainerLight,
    onPrimaryContainer = OnGreenContainerLight,
    onSecondary = OnGreenSecondaryLight,
    secondaryContainer = GreenSecondaryContainerLight,
    onSecondaryContainer = OnGreenSecondaryContainerLight,
    onTertiary = OnGreenTertiaryLight,
    onTertiaryContainer = OnGreenTertiaryContainerLight,
    tertiaryContainer = GreenTertiaryContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    outline = OutlineLight,
)

