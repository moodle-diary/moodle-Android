package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.wiseSentence
import eu.tutorial.moodle.ui.theme.contentGray
import eu.tutorial.moodle.ui.theme.mainOrange

@Composable
fun SentenceComponent(
    modifier: Modifier,
) {
    val random = java.util.Random()

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = wiseSentence[random.nextInt(wiseSentence.size)],
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = mainOrange,
                lineHeight = 20.sp
            )
        )
        Text(
            text = "마르셀 프루스트",
            modifier = Modifier.padding(top = 4.dp),
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(300),
                color = contentGray,
            )
        )
    }
}
