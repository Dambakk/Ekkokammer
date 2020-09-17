package net.dambakk.ekkokammer.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.dambakk.ekkokammer.common.Greeting
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.setContent
import net.dambakk.ekkokammer.android.theme.EkkoTheme
import net.dambakk.ekkokammer.common.allArticles

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "Yes hello")

//            EkkoTheme {

//                Scaffold {
//                    allArticles.forEach {
//                        ArticleSmall(
//                            title = it.title,
//                            provider = it.provider,
//                            imageUrl = it.imageUrl,
//                            publishedDateTime = "now"
//                        )
//                    }
//                }

//            }
        }
    }
}
