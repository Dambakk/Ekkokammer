package net.dambakk.ekkokammer.android.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

val primaryPurple = Color(0xFF541B44)
val secondaryPurple = Color(0xFFFFE5F8)

val darkCardBackground = Color(0xFF2C252A)
val darkChipBackground = Color(0xFF3E383C)
val pink = Color(0xFFE5A4D3)

@Composable
fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}

val Yellow800 = Color(0xFFF29F05)
val Red300 = Color(0xFFEA6D7E)


val EkkoColors = darkColors(
    primary = primaryPurple,
    onPrimary = Color.Black,
    primaryVariant = primaryPurple,
    secondary = primaryPurple,
    onSecondary = Color.Black,
    error = Red300,
    onError = Color.Black
)

val Red700 = Color(0xffdd0d3c)
val Red800 = Color(0xffd00036)
val Red900 = Color(0xffc20029)
val Red200 = Color(0xfff297a2)

val textLightGrey = Color(0x595959)

val LightColors = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800
)

val DarkColors = darkColors(
    primary = primaryPurple,
    primaryVariant = Red700,
    background = darkCardBackground,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.White,
    error = Red200,
)