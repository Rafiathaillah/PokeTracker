package com.visualprogramming.poketracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PokeRedPrimary,
    onPrimary = Color.White,
    primaryContainer = PokeRedHover,
    onPrimaryContainer = Color.White,
    secondary = PokeGreen,
    onSecondary = Color.White,
    secondaryContainer = PokeGreenBg,
    onSecondaryContainer = PokeGreen,
    background = PokeDarkBackground,
    onBackground = Slate100,
    surface = PokeDarkSurface,
    onSurface = Slate100,
    surfaceVariant = PokeDarkCard,
    onSurfaceVariant = Slate300,
    outline = PokeCardBorder
)

@Composable
fun PokeTrackerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
