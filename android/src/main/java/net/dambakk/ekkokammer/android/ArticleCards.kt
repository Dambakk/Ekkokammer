package net.dambakk.ekkokammer.android

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import net.dambakk.ekkokammer.common.Article
import net.dambakk.ekkokammer.common.article1
import java.util.*

@Composable
@Preview
fun ArticleCardSmall(
    @PreviewParameter(ArticleProvider::class) article: Article
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row {
            Column {
                CoilImageWithCrossfade(
                    data = article.imageUrl,
                    modifier = Modifier
                        .padding(16.dp)
                        .height(64.dp)
                        .width(64.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            ) {
                Row {
                    val firstLineTextStyle = TextStyle(fontSize = 12.sp )
                    Text(text = article.provider.capitalize(Locale.getDefault()), style = firstLineTextStyle)
                    Text(text = " • ", style = firstLineTextStyle)
                    Text(text = "2 minutter siden", style = firstLineTextStyle)
                }
                Text(
                    text = article.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600)
                    ),
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    }
}

class ArticleProvider : PreviewParameterProvider<Article> {
    override val values: Sequence<Article>
        get() = sequenceOf(article1)

    override val count: Int
        get() = 1
}