package com.debduttapanda.bottombarnestednavigationwithtopappbartitlechange.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xfff44336),
    primaryVariant = Color(0xFFDB3226),
    secondary = Color(0xfff44336),
)

private val LightColorPalette = lightColors(
    primary = Color(0xfff44336),
    primaryVariant = Color(0xFFDB3226),
    secondary = Color(0xfff44336),

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BottomBarNestedNavigationWithTopAppBarTitleChangeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}