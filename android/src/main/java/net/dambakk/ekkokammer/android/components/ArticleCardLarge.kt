package net.dambakk.ekkokammer.android.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableAmbient
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.imageLoader
import com.prof.rssparser.Article
import dev.chrisbanes.accompanist.coil.CoilImage
import net.dambakk.ekkokammer.android.theme.textLightGrey

@Composable
fun ArticleCardLarge(
    modifier: Modifier = Modifier,
//    @PreviewParameter(ArticleProvider::class)
    article: Article,
    isRead: Boolean,
    showDescriptionIfAvailable: Boolean = false,
    onArticleClicked: (Article) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .then(modifier)
    ) {
        Column(
            modifier = Modifier.clickable(onClick = {
                onArticleClicked(article)
            })
        ) {
            showHeaderImageOrFallback(article)
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
//            ChipRow(article, cardContentModifier.padding(top = 8.dp, bottom = 16.dp))
            if (article.description != null && showDescriptionIfAvailable) {
                Text(
                    text = article.description ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400)
                    ),
                    modifier = Modifier.padding(end = 16.dp, start = 16.dp, top = 16.dp),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun showHeaderImageOrFallback(article: Article) {
    val image = article.image
    if (image != null) {
        CoilImage(
            data = image,
            modifier = Modifier.fillMaxWidth().height(182.dp),
            imageLoader = ContextAmbient.current.imageLoader,
            fadeIn = true,
            contentScale = ContentScale.Crop
        )
    } else {
        Spacer(
            modifier = Modifier.fillMaxWidth().height(182.dp).background(textLightGrey)
        )
    }
}

