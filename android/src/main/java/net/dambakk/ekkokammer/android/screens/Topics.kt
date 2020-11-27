package net.dambakk.ekkokammer.android.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.TabConstants.defaultTabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider
import dev.chrisbanes.accompanist.coil.CoilImage
import net.dambakk.ekkokammer.android.AppViewModel
import net.dambakk.ekkokammer.android.R
import net.dambakk.ekkokammer.android.components.HeightSpacer
import net.dambakk.ekkokammer.android.components.HeightSpacerAndNavBarPadding
import net.dambakk.ekkokammer.android.theme.darkCardBackground
import net.dambakk.ekkokammer.android.theme.pink
import net.dambakk.ekkokammer.android.theme.primaryPurple
import net.dambakk.ekkokammer.android.theme.textLightGrey
import net.dambakk.ekkokammer.common.TopicModel


@Composable
fun Topics(
    appViewModel: AppViewModel,
) {
    ScrollableColumn {
        TopicsHeader()

        val selectedTab = remember { mutableStateOf(0) }
        TopicsTabs(selectedTab)

        HeightSpacer(height = 16.dp)
        val currentSearchTerm = remember { mutableStateOf("") }
        OutlinedTextField(
            value = currentSearchTerm.value,
            textStyle = MaterialTheme.typography.body1.copy(
                color = Color.White,
                fontWeight = FontWeight.Normal
            ),
            label = { Text("Søk etter emne", color = Color.White) },
            leadingIcon = {
                CoilImage(
                    data = R.drawable.ic_search,
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            onValueChange = {
                currentSearchTerm.value = it
            }
        )
        HeightSpacer(height = 16.dp)

        Crossfade(current = selectedTab.value, animation = tween(durationMillis = 300)) {
            when (it) {
                0 -> TopicsList(appViewModel, currentSearchTerm.value)
                1 -> TopicsList(appViewModel, currentSearchTerm.value, true)
                else -> throw Exception("Should not happen")
            }
        }
        HeightSpacerAndNavBarPadding(56.dp)
    }
}

@Composable
private fun TopicsTabs(selectedTab: MutableState<Int>) {
    TabRow(
        selectedTabIndex = selectedTab.value, backgroundColor = primaryPurple,
        indicator = { tabPositions ->
            TabConstants.DefaultIndicator(
                color = Color.White,
                modifier = Modifier
                    .defaultTabIndicatorOffset(tabPositions[selectedTab.value])
                    .height(2.dp)
            )
        }
    ) {
        Tab(
            selected = selectedTab.value == 0,
            onClick = { selectedTab.value = 0 },
            unselectedContentColor = pink,
            selectedContentColor = Color.White
        ) {
            Text("Alle emner", modifier = Modifier.padding(all = 16.dp))
        }
        Tab(
            selected = selectedTab.value == 1,
            onClick = { selectedTab.value = 1 },
            unselectedContentColor = pink,
            selectedContentColor = Color.White
        ) {
            Text("Skjult", modifier = Modifier.padding(all = 16.dp))
        }
    }
}

@Composable
private fun TopicsList(
    appViewModel: AppViewModel,
    filter: String,
    onlyHidden: Boolean? = null
) {
    Column {
        val topicsModifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        val topics = appViewModel.topics.observeAsState().value
        topics
            ?.filter { if (onlyHidden == true) !it.isSelected else true }
            ?.filter { it.title.contains(filter) }
            ?.sortedBy { it.title }
            ?.forEach { topic ->
                TopicItem(topicsModifier, topic) { isSelected ->
                    val oldTopic = topics.find { it.title == topic.title }
                    val newTopic = oldTopic?.copy(isSelected = isSelected)
                    if (oldTopic != null && newTopic != null) {
                        appViewModel.sampleTopics.postValue(topics.minus(oldTopic) + setOf(newTopic))
                    }
                }
                Divider(color = textLightGrey)
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
fun TopicItem(
    paddingModifier: Modifier = Modifier,
    @PreviewParameter(TopicProvider::class) topic: TopicModel,
    onTopicSelectedChanged: (Boolean) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .toggleable(value = topic.isSelected, onValueChange = {
                onTopicSelectedChanged(it)
            })
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
            data = if (topic.isSelected) R.drawable.ic_eye_on else R.drawable.ic_eye_off,
            alignment = Alignment.CenterEnd,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)// Alignment.CenterHorizontally)
        )
    }
}
