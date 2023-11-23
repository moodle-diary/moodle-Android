package eu.tutorial.moodle.ui.chart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RateBoxItem(
    boxColor: Color
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 8.dp)
            .width(36.dp)
            .height(36.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .background(
                color = boxColor
            )
    )
}
