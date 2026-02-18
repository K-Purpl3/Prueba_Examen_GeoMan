package com.example.prueba_examen_georgemanuel.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Colores del enunciado
val Color1 = Color(0xFF82A1DA)
val Color2 = Color(0xFF557B2F)
val Color3 = Color(0xFF344C92)
val Color4 = Color(0xFF1C2751)
val Color5 = Color(0xFF8A9586)
val Color6 = Color(0xFF4898BB)
val Color7 = Color(0xFF308B85)
val Color8 = Color(0xFF364889)

private val LightColorScheme = lightColorScheme(
    primary = Color3,
    onPrimary = Color.White,
    primaryContainer = Color1,
    onPrimaryContainer = Color4,
    secondary = Color6,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD0E8F5),
    onSecondaryContainer = Color4,
    tertiary = Color7,
    onTertiary = Color.White,
    background = Color(0xFFF5F7FF),
    surface = Color(0xFFFFFFFF),
    onBackground = Color4,
    onSurface = Color4,
    error = Color(0xFFB00020),
    onError = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary = Color1,
    onPrimary = Color4,
    primaryContainer = Color8,
    onPrimaryContainer = Color1,
    secondary = Color6,
    onSecondary = Color4,
    tertiary = Color7,
    onTertiary = Color4,
    background = Color(0xFF1A1C2A),
    surface = Color(0xFF252840),
    onBackground = Color(0xFFE0E4FF),
    onSurface = Color(0xFFE0E4FF),
)

@Composable
fun EmpleoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
