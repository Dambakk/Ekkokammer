package net.dambakk.ekkokammer.android.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun EkkoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = EkkoColors,
        shapes = EkkoShapes,
        content = content
    )
}