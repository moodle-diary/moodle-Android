package eu.tutorial.moodle.ui.chart

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.DescriptionDto
import eu.tutorial.moodle.ui.chart.component.RowRankItem
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopRankCard(
    maxList: List<DescriptionDto>
) {
    val today = LocalDate.now()
    val currentMonth = today.monthValue

    Column(
        modifier = Modifier
            .height(200.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "%d월 1등".format(currentMonth),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = Color(0xFFDFDFDF),
            modifier = Modifier.padding(bottom = 2.dp, start = 50.dp)
        )
        Text(
            text = "%d월달 가장 잦았던 감정과 활동들이에요".format(currentMonth),
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = Color(0xFFDFDFDF),
            modifier = Modifier.padding(bottom = 16.dp, start = 50.dp)
        )

        RowRankItem(
            listState = maxList
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TopRankCardPreview() {
//    TopRankCard()
}