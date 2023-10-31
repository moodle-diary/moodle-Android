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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WhyGrid(
    diaryUiState: DiaryUiState,
    valueChange : (DiaryDetails) -> Unit
) {
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
                text = "왜",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                textAlign = TextAlign.Center,
                color = Color(0XFFEDEDED)
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "왜 그랬을까요?",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Center,
                color = Color(0XFFEDEDED)
            )


            if (textVisible) {
                FilledTextBox { visible -> textVisible = visible}
            } else {
                EmptyTextBox(diaryUiState = diaryUiState, valueChange = valueChange)
            }
        }
    }
}