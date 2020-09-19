package net.dambakk.ekkokammer.android.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialElevationScale
import net.dambakk.ekkokammer.android.AppViewModel
import net.dambakk.ekkokammer.android.R
import net.dambakk.ekkokammer.android.components.ArticleCardLarge
import net.dambakk.ekkokammer.android.components.ArticleCardSmall
import net.dambakk.ekkokammer.android.theme.EkkoTheme
import net.dambakk.ekkokammer.android.theme.primaryPurple
import net.dambakk.ekkokammer.common.Article
import net.dambakk.ekkokammer.common.allArticles
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val appViewModel: AppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        exitTransition = MaterialElevationScale(true)
        reenterTransition = MaterialElevationScale(false)
        (view as ViewGroup).setContent(Recomposer.current()) {

            val articlesRead = appViewModel.articlesRead.observeAsState()

            val onArticleClicked: (String) -> Unit = { url ->
                appViewModel.addArticleRead(url)
                Log.d("DashboardFragment", "😀 Number of articles read: ${appViewModel.articlesRead.value?.size}")
                val args = bundleOf("articleUrl" to url)
                findNavController().navigate(R.id.navigation_article, args)
            }

            val shuffled = allArticles.shuffled()
            Dashboard(onArticleClicked, shuffled, articlesRead)
        }
        return view
    }

    @Composable
    fun Dashboard(
        onArticleClicked: (String) -> Unit,
        articles: List<Article>,
        articlesRead: State<MutableList<String>?>
    ) {
        EkkoTheme {
            ScrollableColumn {
                EkkoHeader()
                ScrollableRow(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
                    ArticleCardLarge(article = articles[0], isRead = articlesRead.containsArticleUrl(articles[0].originalUrl), onArticleClicked)
                    ArticleCardLarge(article = articles[1], isRead = articlesRead.containsArticleUrl(articles[1].originalUrl), onArticleClicked)
                    ArticleCardLarge(article = articles[2], isRead = articlesRead.containsArticleUrl(articles[2].originalUrl), onArticleClicked)
                }
                articles.drop(3).forEach {
                    ArticleCardSmall(it, isRead = articlesRead.containsArticleUrl(it.originalUrl), onArticleClicked)
                }
                Spacer(modifier = Modifier.height(16.dp))
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
                .align(Alignment.Bottom)
                .padding(24.dp)
        ) {
            Text(
                text = "Ekkokammer".toUpperCase(),
                style = MaterialTheme.typography.h5.copy(color = Color.White)
            )
        }
    }
}


fun State<MutableList<String>?>.containsArticleUrl(url: String): Boolean {
    return value?.contains(url) ?: false
}