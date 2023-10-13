package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R

@Composable
fun EmotionComponent(
    emotion : Int
){
    val emotionItem = if (emotion != 0) emotion.toString() else "오늘 기분이 어때요?"

    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .background(color = Color(0XFF151515)),
    ) {
        Column(
            modifier = Modifier
                .height(300.dp)
                .fillMaxSize()
                .background(color = Color(0XFF151515)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(70.dp)
                    .fillMaxSize()
                    .background(Color(0XFF1E1E1E)),
                ) {
                Image(
                    painter = painterResource(id = R.drawable.plusbutton),
                    contentDescription = "plus",
                    modifier = Modifier.size(40.dp)
                )
            }

            Text(
                text = emotionItem,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFFDFDFDF)
                ),
            )
        }
    }
}
