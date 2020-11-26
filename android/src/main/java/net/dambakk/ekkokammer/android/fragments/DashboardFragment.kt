package net.dambakk.ekkokammer.android.fragments

import android.util.Log
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import net.dambakk.ekkokammer.android.AppViewModel
import net.dambakk.ekkokammer.android.components.ArticleCardLarge
import net.dambakk.ekkokammer.android.theme.primaryPurple
import net.dambakk.ekkokammer.common.Article
import net.dambakk.ekkokammer.common.allArticles
import java.util.*

@Composable
fun Dashboard(
    navController: NavController,
    appViewModel: AppViewModel,
) {
    val articlesRead = appViewModel.articlesRead.observeAsState()

    val onArticleClicked: (Article) -> Unit = { article ->
        appViewModel.addArticleRead(article.originalUrl)
        Log.d(
            "Dashboard",
            "😀 Number of articles read: ${appViewModel.articlesRead.value?.size}"
        )
        navController.navigate("article/${article.originalUrl}")
    }

    val shuffled = allArticles.shuffled()

    ScrollableColumn {
        EkkoHeader()
        ScrollableRow(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            ArticleCardLarge(
                article = shuffled[0],
                isRead = articlesRead.containsArticleUrl(shuffled[0].originalUrl),
                onArticleClicked
            )
//                    ArticleCardLarge(article = articles[1], isRead = articlesRead.containsArticleUrl(articles[1].originalUrl), onArticleClicked)
//                    ArticleCardLarge(article = articles[2], isRead = articlesRead.containsArticleUrl(articles[2].originalUrl), onArticleClicked)
        }
//                articles.drop(3).forEach {
//                    ArticleCardSmall(it, isRead = articlesRead.containsArticleUrl(it.originalUrl), onArticleClicked)
//                }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun EkkoHeader() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .height(145.dp)
            .fillMaxWidth()
            .background(primaryPurple)
            .padding(24.dp)
    ) {
        Text(
            text = "Ekkokammer".toUpperCase(Locale.forLanguageTag("nb")),
            style = MaterialTheme.typography.h5.copy(color = Color.White)
        )
    }
}


fun State<MutableList<String>?>.containsArticleUrl(url: String): Boolean {
    return value?.contains(url) ?: false
}