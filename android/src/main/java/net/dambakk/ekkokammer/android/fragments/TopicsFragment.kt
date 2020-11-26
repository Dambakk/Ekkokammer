package net.dambakk.ekkokammer.android.fragments

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import net.dambakk.ekkokammer.android.R
import net.dambakk.ekkokammer.android.theme.darkCardBackground
import net.dambakk.ekkokammer.android.theme.primaryPurple
import net.dambakk.ekkokammer.common.TopicModel
import net.dambakk.ekkokammer.common.sampleTopics


@Composable
fun Topics() {
    ScrollableColumn {
        TopicsHeader()
        TopicsList()
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "This app is written using Jetpack Compose 🚀",
            color = Color.White,
            modifier = Modifier.padding(24.dp)
        )
        Spacer(modifier = Modifier
            .height(40.dp) // TODO: This does not seem to work correctly
            .navigationBarsHeight()
        )
    }
}

@Composable
private fun TopicsList() {
    Column {
        val topicsModifier =
            Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
        val topics = remember { sampleTopics }
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
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
                .align(Alignment.CenterVertically)// Alignment.CenterHorizontally)
        )
    }
}
