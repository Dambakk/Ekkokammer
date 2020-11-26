package net.dambakk.ekkokammer.android.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.dambakk.ekkokammer.android.components.Chip
import net.dambakk.ekkokammer.android.components.ChipModel


@Composable
fun ArticleView(articleUrl: String?) {
    // TODO: Need a webview here
    Column(modifier = Modifier.padding(24.dp)) {
        Spacer(modifier = Modifier.height(32.dp))
        articleUrl?.let { Text(it, color = Color.White) }
        Text(
            text = "Denne artikkelen er markert med disse emnene:",
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Chip(model = ChipModel("Sample chip", 69, true))

    }
}