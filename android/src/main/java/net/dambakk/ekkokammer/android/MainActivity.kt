package net.dambakk.ekkokammer.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.dambakk.ekkokammer.android.theme.EkkoTheme
import net.dambakk.ekkokammer.android.theme.primaryPurple
import net.dambakk.ekkokammer.common.Greeting
import net.dambakk.ekkokammer.common.allArticles

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Going edge-to-edge
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        setContent {
            EkkoTheme {
                Column {
                    EkkoHeader {
                        Text(
                            text = "Ekkokammer".toUpperCase(),
                            modifier = Modifier.align(Alignment.Bottom),
                            style = MaterialTheme.typography.h5.copy(color = Color.White)
                        )
                    }
                    LazyColumnFor(items = allArticles) {
                        ArticleCardSmall(it)
                    }
                }
            }
        }
    }

    @Composable
    fun EkkoHeader(content: @Composable () -> Unit) {
        Row(
            modifier = Modifier
                .height(137.dp)
                .fillMaxWidth()
                .background(primaryPurple)
                .padding(24.dp)
        ) {
            content()
        }
    }
}
