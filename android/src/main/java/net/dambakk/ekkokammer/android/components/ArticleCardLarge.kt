package net.dambakk.ekkokammer.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import coil.imageLoader
import com.prof.rssparser.Article
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ArticleCardLarge(
//    @PreviewParameter(ArticleProvider::class)
    article: Article,
    isRead: Boolean,
    onArticleClicked: (Article) -> Unit
) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier.clickable(onClick = {
                onArticleClicked(article)
            })
        ) {
            article.image?.let {

                CoilImage(
                    data = it,
                    modifier = Modifier.fillMaxWidth().height(182.dp),
                    imageLoader = ContextAmbient.current.imageLoader,
                    fadeIn = true,
                    contentScale = ContentScale.Crop
                )
            }
            val cardContentModifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ArticleProviderAndPublishedRow(
                article,
                cardContentModifier.padding(top = 16.dp),
                isRead
            )
            Text(
                text = article.title ?: "No title",
                style = MaterialTheme.typography.h4,
                modifier = cardContentModifier.padding(top = 4.dp)
            )
            ChipRow(article, cardContentModifier.padding(top = 8.dp, bottom = 16.dp))
        }
    }
}

