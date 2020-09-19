package net.dambakk.ekkokammer.android.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import net.dambakk.ekkokammer.common.Article

@Composable
fun ArticleCardLarge(
    @PreviewParameter(ArticleProvider::class) article: Article,
    isRead: Boolean,
    onArticleClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier.clickable(onClick = {
                onArticleClicked(article.originalUrl)
            })
        ) {
            CoilImageWithCrossfade(
                data = article.imageUrl,
                modifier = Modifier.fillMaxWidth().height(182.dp),
                contentScale = ContentScale.Crop
            )
            val cardContentModifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ArticleProviderAndPublishedRow(article, cardContentModifier.padding(top = 16.dp), isRead)
            Text(
                text = article.title,
                style = MaterialTheme.typography.h4,
                modifier = cardContentModifier.padding(top = 4.dp)
            )
            ChipRow(article, cardContentModifier.padding(top = 8.dp, bottom = 16.dp))
        }
    }
}

