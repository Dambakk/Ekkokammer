package net.dambakk.ekkokammer.android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider
import dev.chrisbanes.accompanist.coil.CoilImage
import net.dambakk.ekkokammer.android.R
import net.dambakk.ekkokammer.android.theme.darkCardBackground
import net.dambakk.ekkokammer.android.theme.primaryPurple

class TopicsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_topics, container, false)
        (view as ViewGroup).setContent(Recomposer.current()) {
            TopicsView()
        }
        return view
    }

    @Composable
    private fun TopicsView() {
        Column {
            TopicsHeader()

            val topicsModifier =
                Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
            val topics = remember {
                listOf(
                    TopicModel("Politikk", true),
                    TopicModel("Sport", false),
                    TopicModel("Trump", false),
                    TopicModel("MeToo", true),
                    TopicModel("Klima", true),
                    TopicModel("Hurtigruta", false),
                    TopicModel("Norge", true),
                )
            }
            topics.forEach {
                TopicItem(topicsModifier, it)
                Divider(color = darkCardBackground)
            }
        }
    }

    @Composable
    private fun TopicsHeader() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(145.dp)
                .background(primaryPurple)
        ) {
            Text(
                text = "Emner",
                modifier = Modifier.padding(24.dp).align(Alignment.Bottom),
                style = MaterialTheme.typography.h5.copy(color = Color.White)
            )

        }
    }

    data class TopicModel(
        val title: String,
        val isSelected: Boolean
    )

    class TopicProvider : PreviewParameterProvider<TopicModel> {
        override val values: Sequence<TopicModel>
            get() = sequenceOf(TopicModel("Tittel", true))
    }

    @Composable
    @Preview
    fun TopicItem(
        paddingModifier: Modifier = Modifier,
        @PreviewParameter(TopicProvider::class) topic: TopicModel
    ) {
        val (isSelected, setSelected) = remember { mutableStateOf(topic.isSelected) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
                .toggleable(value = isSelected, onValueChange = setSelected)
                .then(paddingModifier)
        ) {
            Text(
                text = topic.title,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                style = MaterialTheme.typography.body1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.fillMaxWidth())
            CoilImage(
                data = if (isSelected) R.drawable.ic_eye_on else R.drawable.ic_eye_off,
                alignment = Alignment.CenterEnd,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}