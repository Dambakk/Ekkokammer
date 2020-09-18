package net.dambakk.ekkokammer.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import net.dambakk.ekkokammer.android.theme.EkkoTheme
import net.dambakk.ekkokammer.common.Greeting
import net.dambakk.ekkokammer.common.allArticles

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EkkoTheme {
                    Column {
                        Text(text = "Ekkokammer", style = TextStyle(fontSize = 24.sp) )
                        LazyColumnFor(items = allArticles) {
                            ArticleCardSmall(it)
                        }
                    }
            }
        }
    }
}
