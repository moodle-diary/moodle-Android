package eu.tutorial.moodle.ui.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R

@Composable
fun EmptyTextBox(
    diaryUiState : DiaryUiState,
    valueChange : (DiaryDetails) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color = 0Xff212122)),
    ) {
        BasicTextField(
            value = diaryUiState.diaryDetails.diaryText,
            onValueChange = {
                valueChange(
                    diaryUiState.diaryDetails.copy(
                        diaryText = it,
                    )
                )
            }, //{ onValueChange(itemDetails.copy(name = it)) }
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color(0XFFEDEDED)
            ),
            cursorBrush = SolidColor(Color(0XFFEDEDED)),
            modifier = Modifier
                .padding(16.dp)
                .background(Color(color = 0Xff212122)),
            decorationBox = { innerTextField ->
                if (diaryUiState.diaryDetails.diaryText.isEmpty()) {
                    Text(
                        text = "이곳에 생각을 작성하세요.",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color(0XFF888888)
                    )
                }
                innerTextField()
            }
        )
    }

}