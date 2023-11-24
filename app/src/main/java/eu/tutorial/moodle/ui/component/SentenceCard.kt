package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorial.moodle.R

@Composable
fun SentenceComponent(
    emotion: Int = 0,
    modifier: Modifier,
    navController: NavController
) {
    val emotionItem = if (emotion != 0) emotion.toString() else "기분이 어떠신가요?"

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "“행복은 몸에 좋다, 그러나\n마음의 힘을 길러주는 것은 슬픔이다”",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0XFFF2994A),
                lineHeight = 20.sp
            )
        )
        Text(
            text = "마르셀 프루스트",
            modifier = Modifier.padding(top = 4.dp),
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(300),
                color = Color(0XFF828282),
            )
        )
    }
}
