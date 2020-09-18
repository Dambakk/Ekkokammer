package net.dambakk.ekkokammer.android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import net.dambakk.ekkokammer.android.R

class TopicsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_topics, container, false)
        (view as ViewGroup).setContent(Recomposer.current()) {
            Column {
                TopicItem("Politikk", true)
                TopicItem("Sport", true)
                TopicItem("Trump", true)
                TopicItem("MeToo", true)
                TopicItem("Klima", true)
            }
        }
        return view
    }


    @Composable
    fun TopicItem(title: String, isSelected: Boolean) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                style = MaterialTheme.typography.body1.copy(color = Color.White)
            )
        }
    }
}