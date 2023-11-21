package eu.tutorial.moodle.ui.chart.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import eu.tutorial.moodle.data.TimeItem
import eu.tutorial.moodle.data.local.timeData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeChartCard(
    timeList: List<TimeItem> = emptyList()
) {
    Box(
        modifier = Modifier
            .padding(12.dp, 8.dp)
            .clip(RoundedCornerShape(18.dp))
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(top = 16.dp, bottom = 12.dp)
            .height(155.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            timeList.map {
                it.cnt
            }
        }
        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            timeList.map {
                EvalTimeBar(
                    chartSize = it.cnt * 5,
                    time = it.hour
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EvalTimeBar(
    chartSize: Int,
    time: Int,
) {
    Box(
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .height(chartSize.dp)
                    .background(color = Color(0xFFF2994A))
            )
            if (time % 2 == 0) {
                Text(
                    text = time.toString(),
                    modifier = Modifier.height(20.dp),
                    fontSize = 12.sp,
                    color = Color(0xFF828282),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(700)
                )
            } else {
                Spacer(
                    modifier =
                    Modifier
                        .height(20.dp)
                        .width(10.dp)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
)
@Composable
fun ChartPreview() {
    TimeChartCard(

    )
}