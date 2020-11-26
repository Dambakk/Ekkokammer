package net.dambakk.ekkokammer.android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import net.dambakk.ekkokammer.android.R
import net.dambakk.ekkokammer.android.components.Chip
import net.dambakk.ekkokammer.android.components.ChipModel

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.get("articleUrl") as String

        view.findViewById<WebView>(R.id.articleWebView).apply {
            loadUrl(url)
        }

        view.findViewById<ConstraintLayout>(R.id.articleComposeView)
            .setContent(Recomposer.current()) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Denne artikkelen er markert med disse emnene:",
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Chip(model = ChipModel("Sample chip", 69, true))

                }
            }

    }
}