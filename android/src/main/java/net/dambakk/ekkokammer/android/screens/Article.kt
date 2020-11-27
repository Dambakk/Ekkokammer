package net.dambakk.ekkokammer.android.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import net.dambakk.ekkokammer.android.components.Chip
import net.dambakk.ekkokammer.android.components.ChipModel


@ExperimentalAnimationApi
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Article(articleUrl: String?) {
    val showMetaInfo = remember { mutableStateOf(true) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (articleUrl != null) {
            AndroidView(viewBlock = ::WebView) { webView ->
                with(webView) {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    loadUrl(articleUrl)
                    setOnScrollChangeListener { view, scrollX, scrollY, oldX, oldY ->
                        val scrollAmount = scrollY - oldY
                        if (scrollAmount >= 30) {
                            showMetaInfo.value = false
                        } else if (scrollAmount <= -30) {
                            showMetaInfo.value = true
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            AnimatedVisibility(
                visible = showMetaInfo.value,
                enter = slideIn(
                    { fullSize -> IntOffset(0, 600) },
                    tween(500, easing = LinearOutSlowInEasing)
                ),
                exit = slideOut(
                    { IntOffset(0, 600) },
                    tween(500, easing = LinearOutSlowInEasing)
                )
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .navigationBarsPadding()
                        .padding(bottom = 56.dp) // app navbar height
                        .background(color = MaterialTheme.colors.surface)
                ) {
                    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                        Spacer(modifier = Modifier.height(32.dp))
                        articleUrl?.let { Text(it, color = Color.White) }
                        Text(
                            text = "Denne artikkelen er markert med disse emnene:",
                            color = Color.White,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Chip(model = ChipModel("Sample chip", 69, true))
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}
