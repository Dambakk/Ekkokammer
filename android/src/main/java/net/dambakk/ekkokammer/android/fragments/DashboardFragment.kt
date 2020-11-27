package net.dambakk.ekkokammer.android.fragments

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
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
    val articlesRead = appViewModel.articlesRead.observeAsState()
    val articles = appViewModel.nrkArticlesLiveData.observeAsState().value

    val onArticleClicked: (Article) -> Unit = { article ->
        appViewModel.addArticleRead(article.link ?: "no-link")
        Log.d(
            "Dashboard",
            "😀 Number of articles read: ${appViewModel.articlesRead.value?.size}"
        )
        navController.navigate("article/${article.link}")
    }

    if (articles.isNullOrEmpty()) {
        Text(text = "Loading...", color = Color.White)
    } else {

        ScrollableColumn {
            EkkoHeader()
            ScrollableRow(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
                articles.subList(0, 3).forEach {
                    ArticleCardLarge(
                        modifier = Modifier.width(350.dp),
                        article = it,
                        isRead = articlesRead.containsArticle(it.link),
                        onArticleClicked = onArticleClicked
                    )
                }
            }
            articles.drop(3).forEach {
                val expanded = remember { mutableStateOf(false) }
                Box(modifier = Modifier.animateContentSize()) {
                    if (!expanded.value) {
                        ArticleCardSmall(
                            it,
                            isRead = articlesRead.containsArticle(it.link),
                            onArticleClicked,
                        ) { shouldBeExpanded ->
                            expanded.value = shouldBeExpanded
                        }

                    } else {
                        ArticleCardLarge(
                            modifier = Modifier
                                .fillMaxWidth(),
                            article = it,
                            isRead = articlesRead.containsArticle(it.link),
                            showDescriptionIfAvailable = true,
                            onArticleClicked = {
                                expanded.value = false
                            }
                        )
                    }
                }
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