package net.dambakk.ekkokammer.android.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.PreviewParameterProvider
import net.dambakk.ekkokammer.common.Article
import net.dambakk.ekkokammer.common.article1
import java.util.*

@Composable
fun ArticleProviderAndPublishedRow(
    article: Article,
    modifier: Modifier = Modifier,
    isRead: Boolean = false
) {
    Row(modifier = modifier) {
        val firstLineTextStyle = TextStyle(fontSize = 12.sp)
        Text(text = article.provider.capitalize(Locale.getDefault()), style = firstLineTextStyle)
        Text(text = " • ", style = firstLineTextStyle)
        Text(text = "2 minutter siden", style = firstLineTextStyle)
        if (isRead) {
            Text(text = " • ", style = firstLineTextStyle)
            Text(text = "Ferdig lest ✔︎", style = firstLineTextStyle)
        }
    }
}


@Composable
fun ChipRow(article: Article, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        article.classifications.forEach {
            Chip(model = ChipModel(text = it.title, numVotes = it.votes))
        }
    }
}

class ArticleProvider : PreviewParameterProvider<Article> {
    override val values: Sequence<Article>
        get() = sequenceOf(article1)

    override val count: Int
        get() = 1
}