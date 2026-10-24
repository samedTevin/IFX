package com.samedtevin.ifx.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = IfxLime,
    onPrimary = IfxBlack,
    secondary = IfxLime,
    onSecondary = IfxBlack,
    tertiary = IfxLime,
    background = IfxBlack,
    onBackground = IfxWhite,
    surface = IfxCardDark,
    onSurface = IfxWhite
)

private val LightColorScheme = lightColorScheme(
    primary = IfxBlack,
    onPrimary = IfxLime,
    secondary = IfxLime,
    onSecondary = IfxBlack,
    tertiary = IfxLime,
    background = IfxBackground,
    onBackground = IfxBlack,
    surface = IfxWhite,
    onSurface = IfxBlack,
    outline = IfxMuted
)

@Composable
fun IFXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
