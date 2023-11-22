package eu.tutorial.moodle.ui.chart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.theme.contentGray
import eu.tutorial.moodle.ui.theme.nothingGray

@Composable
fun RateCategoryItem(
    textValue: String,
    boxColor: Color
) {
    Row(
        modifier = Modifier.padding(start = 24.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .background(
                    color = boxColor,
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .padding(end = 10.dp)
        )
        Text(
            text = textValue,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(700),
            color = contentGray,
        )
    }
}