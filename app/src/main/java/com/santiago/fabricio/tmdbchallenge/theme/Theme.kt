package com.santiago.fabricio.tmdbchallenge.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = yellow,
    primaryContainer = BlackPrimary,
    background = BlackPrimary,
    secondary = white,
)

private val LightColorPalette = lightColorScheme(
    primary = BlackPrimary,
    primaryContainer = BlackSecondary,
    background = white,
    secondary = black,
)

@Composable
fun BaseTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}