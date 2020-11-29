package net.dambakk.ekkokammer.android.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnit.Companion.Sp
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import net.dambakk.ekkokammer.android.AppViewModel
import net.dambakk.ekkokammer.android.components.ArticleCardLarge
import net.dambakk.ekkokammer.android.components.ArticleCardSmall
import net.dambakk.ekkokammer.android.theme.primaryPurple
import kotlin.random.Random

@ExperimentalAnimationApi
@Composable
fun Dashboard(
    navController: NavController,
    appViewModel: AppViewModel,
) {
    val scrollState = rememberScrollState(0f)
    val useCollapsingHeader = remember { mutableStateOf(false) }
    val articlesRead = appViewModel.articlesRead.observeAsState()
    val articles = appViewModel.nrkArticlesLiveData.observeAsState().value

    val dynamicTextSize = if(useCollapsingHeader.value) (25.sp - Sp(scrollState.value / 20)).coerceIn(10.sp, 25.sp) else 25.sp

    ScrollableColumn(scrollState = scrollState) {
        EkkoHeader(useCollapsingHeader, dynamicTextSize)
        if (articles.isNullOrEmpty()) {
            Text(text = "Loading...", color = Color.White)
        } else {
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
                val expanded = remember { mutableStateOf(Random.nextInt(5) % 5 == 0) }
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
fun EkkoHeader(useCollapsingHeader: MutableState<Boolean>, dynamicTextSize: TextUnit = 25.sp) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .height(145.dp)
            .fillMaxWidth()
            .clickable(onClick = { useCollapsingHeader.value = !useCollapsingHeader.value })
            .background(primaryPurple)
            .padding(24.dp)
    ) {
        Text(
            text = "EKKOKAMMER",
            fontSize = if (useCollapsingHeader.value) dynamicTextSize else 25.sp,
            color = Color.White,
            style = MaterialTheme.typography.h5//.copy(color = Color.White)
        )
    }
}


fun State<MutableSet<String>?>.containsArticle(url: String?): Boolean {
    if (url == null) return false
    return value?.contains(url) ?: false
}