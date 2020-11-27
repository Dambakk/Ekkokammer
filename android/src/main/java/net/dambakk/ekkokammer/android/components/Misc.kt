package net.dambakk.ekkokammer.android.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun HeightSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun HeightSpacerAndNavBarPadding(height: Dp) {
    Spacer(modifier = Modifier.navigationBarsPadding().height(height))
}