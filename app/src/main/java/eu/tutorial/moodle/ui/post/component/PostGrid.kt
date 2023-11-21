package eu.tutorial.moodle.ui.post.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.post.DiaryDetails
import eu.tutorial.moodle.ui.post.DiaryUiState
import eu.tutorial.moodle.ui.post.InitialTextBox
import eu.tutorial.moodle.ui.post.SaveTextBox
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostGrid(
    diaryUiState: DiaryUiState,
    valueChange: (DiaryDetails) -> Unit
) {
    var textVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(containerGray)
            .clip(RoundedCornerShape(18.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = "고민해보기",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            textAlign = TextAlign.Center,
            color = contentBlack
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "당시 어떤 상황이었나요?",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            textAlign = TextAlign.Center,
            color = contentGray
        )


        if (textVisible) {
            InitialTextBox { visible -> textVisible = visible }
        } else {
            SaveTextBox(
                textValue = diaryUiState.diaryDetails,
                valueChange = valueChange
            )
        }
    }
}