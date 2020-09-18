package net.dambakk.ekkokammer.android.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider
import net.dambakk.ekkokammer.android.theme.*


@Composable
@Preview
fun Chip(@PreviewParameter(ChipPreviewProvider::class) model: ChipModel) {
    Card(
        modifier = Modifier.padding(end = 8.dp),
        backgroundColor = darkChipBackground,
        shape = RoundedCornerShape(50)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 12.dp)
                .wrapContentSize()
        ) {
            Text(text = model.text, style = MaterialTheme.typography.caption)
            model.numVotes?.let {
                Text(
                    text = "+$it",
                    modifier = Modifier.padding(start = 6.dp),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}


data class ChipModel(
    val text: String,
    val numVotes: Int?,
    val isSelected: Boolean = false
)

class ChipPreviewProvider : PreviewParameterProvider<ChipModel> {
    override val values: Sequence<ChipModel>
        get() = sequenceOf(
            ChipModel(text = "Trump", numVotes = 69, isSelected = true),
            ChipModel(text = "Fotball", numVotes = null, isSelected = false)
        )
}