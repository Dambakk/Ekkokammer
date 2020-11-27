package net.dambakk.ekkokammer.android.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import net.dambakk.ekkokammer.android.AppViewModel
import net.dambakk.ekkokammer.android.components.ArticleCardLarge
import net.dambakk.ekkokammer.android.components.ArticleCardSmall
import net.dambakk.ekkokammer.android.theme.primaryPurple
import java.util.*

@ExperimentalAnimationApi
@Composable
fun Dashboard(
    navController: NavController,
    appViewModel: AppViewModel,
) {
    val articlesRead = appViewModel.articlesRead.observeAsState()
    val articles = appViewModel.nrkArticlesLiveData.observeAsState().value

    if (articles.isNullOrEmpty()) {
        Text(text = "Loading...", color = Color.White)
    } else {

        ScrollableColumn {
            EkkoHeader()
            Spacer(modifier = Modifier.height(16.dp))
            ScrollableRow(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
                Spacer(modifier = Modifier.width(16.dp))
                articles.subList(0, 3).forEach {
                    val expanded = remember { mutableStateOf(false) }
                    ArticleCardLarge(
                        modifier = Modifier.width(350.dp),
                        article = it,
                        isRead = articlesRead.containsArticle(it.link),
                        showDescriptionIfAvailable = expanded.value,
                        onArticleClicked = { expanded.value = !expanded.value },
                        openArticleAction = { article ->
                            appViewModel.addArticleRead(article.link ?: "no-link")
                            navController.navigate("article/${article.link}")
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            articles.drop(3).forEach {
                val expanded = remember { mutableStateOf(false) }
                Box(modifier = Modifier.animateContentSize()) {
                    if (!expanded.value) {
                        ArticleCardSmall(it, isRead = articlesRead.containsArticle(it.link)) {
                            expanded.value = true
                        }

                    } else {
                        ArticleCardLarge(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                            article = it,
                            isRead = articlesRead.containsArticle(it.link),
                            showDescriptionIfAvailable = true,
                            onArticleClicked = {
                                expanded.value = false
                            },
                            openArticleAction = { article ->
                                appViewModel.addArticleRead(article.link ?: "no-link")
                                navController.navigate("article/${article.link}")
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