package net.dambakk.ekkokammer.android.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import net.dambakk.ekkokammer.common.Article

@Composable
@Preview
fun ArticleCardLarge(
    @PreviewParameter(ArticleProvider::class) article: Article
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Column {
            CoilImageWithCrossfade(
                data = article.imageUrl,
                modifier = Modifier.fillMaxWidth().height(182.dp)
            )
            val cardContentModifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ArticleProviderAndPublishedRow(article, cardContentModifier.padding(top = 16.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.h4,
                modifier = cardContentModifier.padding(top = 4.dp)
            )
            ChipRow(article, cardContentModifier.padding(top = 8.dp, bottom = 16.dp))
        }
    }
}

