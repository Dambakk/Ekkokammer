package net.dambakk.ekkokammer.android.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prof.rssparser.Article
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ArticleCardSmall(
//    @PreviewParameter(ArticleProvider::class)
    article: Article,
    isRead: Boolean,
    onArticleClicked: (Article) -> Unit
) {

    val expanded = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier.clickable(onClick = {
                if (!expanded.value) {
                    expanded.value = true
                } else {
                    onArticleClicked(article)
                }
            })
        ) {
            Column {
                article.image?.let {
                    CoilImage(
                        data = it,
                        modifier = Modifier
                            .padding(16.dp)
                            .height(64.dp)
                            .width(64.dp),
                        fadeIn = true,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            ) {
                ArticleProviderAndPublishedRow(article, isRead = isRead)
                Text(
                    text = article.title ?: "",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600)
                    ),
                    modifier = Modifier.padding(end = 16.dp)
                )
                ChipRow(article, Modifier.padding(top = 8.dp))

                if (expanded.value) {
                    Text(
                        text = article.description ?: "",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400)
                        ),
                        modifier = Modifier.padding(end = 16.dp),
                    )
                }
            }
        }
    }
}
