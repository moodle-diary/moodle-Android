package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentGray

@Composable
fun EmptyNote(
    isDiaryExist: Boolean
) {
    if (!isDiaryExist) {
        Box(
            modifier = Modifier
                .padding(0.dp, 4.dp)
                .fillMaxWidth()
                .height(82.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(containerGray),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "아직 기록이 없어요",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = contentGray
                ),
            )
        }

    }
}