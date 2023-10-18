package eu.tutorial.moodle.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.diaryText

@Composable
fun NoteComponent() {
    Text(
        text = "기록",
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        color = Color(0XFFDFDFDF),
        modifier = Modifier.padding(top = 24.dp)
    )

    Box(
        modifier = Modifier
            .padding(top = 12.dp)
            .clip(shape = RoundedCornerShape(18.dp))
            .background(Color(0XFF2A292B))
            .fillMaxWidth(),
    ){
        Text(
            text = diaryText,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = Color(0XFFDFDFDF),
            modifier = Modifier.padding(16.dp, 20.dp)
        )
    }

}