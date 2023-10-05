package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val emotionItem = if (emotion != 0) emotion.toString() else "Emotions"

    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp),
        shape = RoundedCornerShape(32.dp)
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .background(color = Color(0XEFEFEFEF)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = emotionItem,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0XFF414141)
                ),
            )
        }
    }
}
