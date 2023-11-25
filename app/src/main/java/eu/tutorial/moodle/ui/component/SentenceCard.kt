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
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.wiseSentencesList
import eu.tutorial.moodle.ui.theme.contentGray
import eu.tutorial.moodle.ui.theme.mainOrange

@Composable
fun SentenceComponent(
    modifier: Modifier,
) {
    val random = java.util.Random()
    val randomIndex = random.nextInt(wiseSentencesList.size)
    val randomSentence = wiseSentencesList[randomIndex]

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "\"${randomSentence.sentence}\"",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = mainOrange,
                lineHeight = 20.sp
            )
        )
        Text(
            text = "${randomSentence.writer}",
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
