package eu.tutorial.moodle.ui.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R

@Composable
fun FilledTextBox(
    changeVisible : (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = { changeVisible(false) },
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

}