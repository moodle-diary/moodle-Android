package eu.tutorial.moodle.ui.calendar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier,
    ) {
        Text(
            text = "Emotion",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontSize = 20.sp
            )
        )
        for(i in 0 until 4){
            EmotionBar()
        }
    }
}

@Composable
fun EmotionBar(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, end = 4.dp)
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Box(
            modifier = Modifier
                .size(40.dp) // 동그란 배경의 크기 설정
                .background(Color(0XFF414141), CircleShape)
            , // 동그란 배경 추가
        )
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = "13",
            style = TextStyle(
                fontSize = 16.sp
            ),
//            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Canvas(
            modifier = Modifier.weight(1f),
        ){
            drawRect(
                color = Color(0XFF414141),
                size = Size(360f, 42f),
                topLeft = Offset(0f, -21f)
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun EmotionChartPreview(){
    EmotionChart()
}