package eu.tutorial.moodle.ui.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R

@Composable
fun PlaceRankCard() {

    var isFolder by remember { mutableStateOf(true) } // 접혀 있으면 true

    Column(
        modifier = Modifier
            .padding(12.dp, 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0XFF212122))
            .padding(top = 16.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(start = 38.dp, end = 23.dp, top = 4.dp, bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "장소 순위",
                fontSize = 16.sp,
                color = Color(0XFFDFDFDF),
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            if (isFolder) {
                Text(
                    text = "더보기",
                    fontSize = 12.sp,
                    color = Color(0XFF7E7E7E),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    modifier = Modifier.clickable{isFolder = false}
                )
            } else {
                Text(
                    text = "접기",
                    fontSize = 12.sp,
                    color = Color(0XFF7E7E7E),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    modifier = Modifier.clickable{isFolder = true}
                )
            }
        }

        if (isFolder) {
            RowRankItem()
        } else {
            ColumnRankItem()
        }
    }
}