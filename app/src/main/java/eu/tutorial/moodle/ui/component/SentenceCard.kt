package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.mainOrange

@Composable
fun SentenceComponent(
    emotion: Int = 0,
    modifier: Modifier,
    navController: NavController
) {
    val emotionItem = if (emotion != 0) emotion.toString() else "기분이 어떠신가요?"

    Column(
        modifier = modifier
    ) {
        Text(
            text = "오늘 한 줄",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = contentBlack,
            modifier = Modifier.padding(8.dp, 12.dp)
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(containerGray)
                .padding(38.dp, 32.dp)
        ) {
            Text(
                text = "“행복은 몸에 좋다, 그러나 마음의 힘을 길러주는 것은 슬픔이다”",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(700),
                    color = mainOrange,
                )
            )
            Text(
                text = "마르셀 프루스트",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = mainOrange,
                )
            )
        }
    }
}
