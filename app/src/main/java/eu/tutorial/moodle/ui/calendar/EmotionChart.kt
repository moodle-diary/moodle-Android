package eu.tutorial.moodle.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R

// TODO 이 부분을 이번 달에 일기를 얼마나 썻는지 통계 표시
@Composable
fun EmotionChart(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp),
    ) {
        Text(
            text = "2일 연속 기록중!",
            modifier = Modifier.padding(top = 24.dp, start = 10.dp),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontSize = 18.sp,
                color = Color(0XFFDFDFDF)
            )
        )
        Text(
            text = "좋아요! 일주일을 채워볼까요?",
            modifier = Modifier.padding(start = 10.dp),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 12.sp,
                color = Color(0XFFDFDFDF)
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for(i in 0 until 7){
                DateBar()
            }
        }
    }
}

@Composable
fun DateBar(){
    Box(
        modifier = Modifier
            .width(38.dp)
            .height(56.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .border(2.dp, Color(0XFF363637), RoundedCornerShape(15.dp))
    ) {

    }

//        Canvas(
//            modifier = Modifier.weight(1f),
//        ){
//            drawRect(
//                color = Color(0XFF414141),
//                size = Size(360f, 42f),
//                topLeft = Offset(0f, -21f)
//            )
//        }
//    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun EmotionChartPreview(){
    EmotionChart()
}