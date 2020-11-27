package net.dambakk.ekkokammer.android.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.imageLoader
import com.prof.rssparser.Article
import dev.chrisbanes.accompanist.coil.CoilImage
import net.dambakk.ekkokammer.android.R

@ExperimentalAnimationApi
@Composable
fun ArticleCardLarge(
    modifier: Modifier = Modifier,
//    @PreviewParameter(ArticleProvider::class)
    article: Article,
    isRead: Boolean,
    showDescriptionIfAvailable: Boolean = false,
    onArticleClicked: (Article) -> Unit = {},
    openArticleAction: (Article) -> Unit
) {
    var showDescription = showDescriptionIfAvailable
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize()
            .then(modifier)
    ) {
        Column(
            modifier = Modifier.clickable(onClick = {
                onArticleClicked(article)
            })
        ) {
            if (article.image.isNullOrBlank()) {
                showDescription = true
            } else {
                showHeaderImageOrFallback(article)
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
//            ChipRow(article, cardContentModifier.padding(top = 8.dp, bottom = 16.dp))
            AnimatedVisibility(visible = article.description != null && showDescription) {
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
            Row(
                modifier = Modifier.align(Alignment.End)
            ) {
                Button(
                    onClick = { openArticleAction(article) },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Text(text = "Les saken", color = Color.White)
                    Spacer(modifier = Modifier.width(4.dp))
                    CoilImage(
                        data = R.drawable.ic_arrow_right,
                        modifier = Modifier.size(18.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
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
//        Spacer(
//            modifier = Modifier.fillMaxWidth().height(182.dp).background(textLightGrey)
//        )
    }
}

