package net.dambakk.ekkokammer.android.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

@Composable
fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}

val Yellow800 = Color(0xFFF29F05)
val Red300 = Color(0xFFEA6D7E)

val primaryPurple = Color(0xFF541B44)

val EkkoColors = darkColors(
    primary = primaryPurple,
    onPrimary = Color.Black,
    primaryVariant = primaryPurple,
    secondary = primaryPurple,
    onSecondary = Color.Black,
    error = Red300,
    onError = Color.Black
)
