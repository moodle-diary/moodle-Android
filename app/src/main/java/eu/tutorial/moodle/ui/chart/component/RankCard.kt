package eu.tutorial.moodle.ui.chart.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.DescriptionDto
import eu.tutorial.moodle.data.TypeDto
import eu.tutorial.moodle.data.local.emotionList
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray

@Composable
fun RankCard(
    rankState: List<DescriptionDto>,
    typeState: List<TypeDto> = emotionList,
    category: String
) {
    var isFolder by remember { mutableStateOf(true) } // 접혀 있으면 true

    Column(
        modifier = Modifier
            .padding(12.dp, 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(containerGray)
            .padding(top = 16.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(start = 38.dp, end = 23.dp, bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category,
                fontSize = 16.sp,
                color = contentBlack,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            if (isFolder) {
                Text(
                    text = "더보기",
                    fontSize = 12.sp,
                    color = contentGray,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    modifier = Modifier.clickable { isFolder = false }
                )
            } else {
                Text(
                    text = "접기",
                    fontSize = 12.sp,
                    color = contentGray,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    modifier = Modifier.clickable { isFolder = true }
                )
            }
        }

        // TODO: RowRankItem 출력 시 rankState 3개로 끊기
        if (isFolder) {
            RowRankItem(
                listState = rankState,
                typeState = typeState
            )
        } else {
            ColumnRankItem(
                listState = rankState,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun EmotionRankCardPreview() {
//    EmotionRankCard()
}