package eu.tutorial.moodle.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.questionList
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray

@Composable
fun NotesComponent(
    text: String = "",
    changeDiaryExist: (Boolean) -> Unit,
) {
    if (text != "\\\\\\\\\\".repeat(3)) {
        Column(
            // TODO 이 부분 코드 개선.. value를 빼서 Composable 줄이는 방향
            modifier = Modifier
                .padding(0.dp, 4.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(containerGray),
        ) {
            text.split("\\\\\\\\\\").mapIndexed() { index, element ->
                Text(
                    text = stringResource(id = questionList[index]),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 18.dp, top = 16.dp),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFF8923)
                )
                Text(
                    text = element,
                    modifier = Modifier
                        .padding(start = 18.dp, end = 18.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 16.sp
                    ),
                    color = contentBlack
                )
            }
        }
        changeDiaryExist(true)
    }
}
