package eu.tutorial.moodle.ui.chart.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.tutorial.moodle.ui.theme.mainOrange

@Composable
fun RateBoxItem(
    modifier: Modifier = Modifier,
    boxColor: Color
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .width(36.dp)
            .height(36.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .background(
                color = boxColor
            )
    )
}