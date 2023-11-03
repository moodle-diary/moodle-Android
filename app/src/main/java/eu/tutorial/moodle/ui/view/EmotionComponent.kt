package eu.tutorial.moodle.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R

@Composable
fun emotionComponent(){
    Text(
        text = "감정 아이콘",
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        color = Color(0XFFDFDFDF),
        modifier = Modifier.padding(top = 24.dp)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(1) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(2) {
                    Image(
                        painter = painterResource(id = R.drawable.angry),
                        contentDescription = "angry",
                        modifier = Modifier
                            .padding(5.dp)
                            .size(58.dp)
                    )
                }
            }
        }
    }

}