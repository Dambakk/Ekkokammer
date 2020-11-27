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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.prof.rssparser.Article
import net.dambakk.ekkokammer.android.AppViewModel
import net.dambakk.ekkokammer.android.components.ArticleCardLarge
import net.dambakk.ekkokammer.android.components.ArticleCardSmall
import net.dambakk.ekkokammer.android.theme.primaryPurple
import java.util.*

@Composable
fun Dashboard(
    navController: NavController,
    appViewModel: AppViewModel,
) {

    val scope = rememberCoroutineScope()

    val articlesRead = appViewModel.articlesRead.observeAsState()

    val onArticleClicked: (Article) -> Unit = { article ->
        appViewModel.addArticleRead(article.guid ?: "no-id")
        Log.d(
            "Dashboard",
            "😀 Number of articles read: ${appViewModel.articlesRead.value?.size}"
        )
        navController.navigate("article/${article.guid}")
    }

//    val shuffled = allArticles.shuffled()
    val articles = appViewModel.nrkArticlesLiveData.observeAsState().value

    if (articles.isNullOrEmpty()) {
        Text(text = "Loading...", color = Color.White)
    } else {
        ScrollableColumn {
            EkkoHeader()
            ScrollableRow(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
                val firstRow = articles.subList(0, 3)
                val (first, second, third) = firstRow
                ArticleCardLarge(
                    article = first,
                    isRead = articlesRead.containsArticle(first.guid),
                    onArticleClicked
                )
                    ArticleCardLarge(article = second, isRead = articlesRead.containsArticle(second.guid), onArticleClicked)
                    ArticleCardLarge(article = third, isRead = articlesRead.containsArticle(third.guid), onArticleClicked)
            }
            articles.drop(3).forEach {
                ArticleCardSmall(it, isRead = articlesRead.containsArticle(it.guid), onArticleClicked)
            }
            Spacer(modifier = Modifier.height((56 + 16 + 16).dp))
        }
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


fun State<MutableSet<String>?>.containsArticle(url: String?): Boolean {
    if (url == null) return false
    return value?.contains(url) ?: false
}