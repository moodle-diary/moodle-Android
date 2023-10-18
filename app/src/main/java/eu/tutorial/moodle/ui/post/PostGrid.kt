package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostGrid(
    diaryUiState: DiaryUiState,
    valueChange : (DiaryDetails) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    var textVisible by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(color = 0Xff212122))
            .clip(shape = CircleShape.copy(all = CornerSize(45.dp)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "생각",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                textAlign = TextAlign.Center,
                color = Color(0XFFEDEDED)
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "오늘 하루 생각을 간단하게 적어두면\n" +
                        "나중에 돌아봤을때 나를 더 잘 알게 될거에요",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Center,
                color = Color(0XFFEDEDED)
            )


            if (textVisible) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = { textVisible = false },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = CircleShape.copy(all = CornerSize(20.dp)))
                            .background(Color(0XFF363637))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "생각",
                            modifier = Modifier.size(24.dp),
                            tint = Color(0XFFEDEDED)
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "작성하기",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color(0XFFEDEDED)
                    )
                }
            } else {
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
        }
    }
}