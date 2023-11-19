package eu.tutorial.moodle.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.ButtonItem
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.unselectedIndicator

@Composable
fun AlarmButton(
    item: ButtonItem,
    isSelected: Boolean,
    onTap: () -> Unit
) {
    val backgroundColor =
        if (isSelected) containerGray
        else unselectedIndicator
    val contentColor =
        if (isSelected) contentBlack
        else Color(0XFF888888)
    val borderColor =
        if (isSelected) containerGray
        else unselectedIndicator

    Box(
        modifier = Modifier
            .padding(top = 6.dp, bottom = 6.dp)
            .fillMaxWidth()
            .height(67.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable { onTap() }
            .background(backgroundColor)
            .padding(start = 22.dp, end = 22.dp)
            .border(1.dp, borderColor, RoundedCornerShape(10.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.label,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = contentColor
            )
        }
    }
}