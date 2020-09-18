package net.dambakk.ekkokammer.android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialElevationScale
import net.dambakk.ekkokammer.android.R
import net.dambakk.ekkokammer.android.components.ArticleCardLarge
import net.dambakk.ekkokammer.android.components.ArticleCardSmall
import net.dambakk.ekkokammer.android.theme.EkkoTheme
import net.dambakk.ekkokammer.android.theme.primaryPurple
import net.dambakk.ekkokammer.common.allArticles
import net.dambakk.ekkokammer.common.article1

class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val onArticleClicked: (String) -> Unit = { url ->
        val args = bundleOf("articleUrl" to url)
        findNavController().navigate(R.id.navigation_article, args)
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
            Dashboard(onArticleClicked)
        }
        return view
    }

    @Composable
    fun Dashboard(onArticleClicked: (String) -> Unit) {
        EkkoTheme {
            ScrollableColumn {
                EkkoHeader {
                    Text(
                        text = "Ekkokammer".toUpperCase(),
                        style = MaterialTheme.typography.h5.copy(color = Color.White)
                    )
                }
                val shuffled = allArticles.shuffled()
                ArticleCardLarge(article = shuffled.first(), onArticleClicked)
                ArticleCardLarge(article = shuffled[1], onArticleClicked)
                shuffled.drop(2).forEach { 
                    ArticleCardSmall(it, onArticleClicked)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    @Composable
    fun EkkoHeader(content: @Composable () -> Unit) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .height(145.dp)
                .fillMaxWidth()
                .background(primaryPurple)
                .align(Alignment.Bottom)
                .padding(24.dp)
        ) {
            content()
        }
    }
}