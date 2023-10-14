package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .height(520.dp)
            .background(Color(color = 0XffEFEFEF))
            .clip(shape = CircleShape.copy(all = CornerSize(45.dp)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 28.dp),
                text = "Notes",
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center
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
                            .background(Color(0XFFFFFFFF))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "create",
                            modifier = Modifier.size(24.dp),
                            tint = Color(0XFF000000)
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "Can you describe your\n" + "day in words?",
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(color = 0XffEFEFEF)),
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
                        textStyle = TextStyle(fontSize = 16.sp),
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color(color = 0XffEFEFEF))
                    )
                }
            }
        }
    }
}