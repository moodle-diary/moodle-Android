package eu.tutorial.moodle.ui.chart.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.timeData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeChartCard() {
    Box(
        modifier = Modifier
            .width(336.dp)
            .height(155.dp)
            .clip(shape = RoundedCornerShape(size = 18.dp))

    ) {
        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            timeData.map {
                EvalTimeBar(
                    chartSize = 87,
                    time = it
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EvalTimeBar(
    chartSize: Int,
    time: String,
) {
    Box(
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .height(chartSize.dp)
                    .background(color = Color(0xFFF2994A))
            )
            Text(
                text = time,
                fontSize = 12.sp,
                color = Color(0xFF828282),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(700)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
)
@Composable
fun ChartPreview() {
    TimeChartCard()
}