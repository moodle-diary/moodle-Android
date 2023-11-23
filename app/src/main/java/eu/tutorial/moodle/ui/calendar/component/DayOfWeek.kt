package eu.tutorial.moodle.ui.calendar.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.theme.contentBlack
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayOfWeek(
    modifier: Modifier = Modifier
) {
    val startDay = java.time.DayOfWeek.SUNDAY

    val days = java.time.DayOfWeek.values()
    var startIndex = days.indexOf(startDay)

    Row(modifier) {

        for (i in startIndex until days.size) {
            val day = days[i]
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = day.getDisplayName(TextStyle.SHORT, Locale.KOREAN).lowercase(),
                textAlign = TextAlign.Center,
                style = androidx.compose.ui.text.TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 12.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                ),
                color = contentBlack
            )
        }

        startIndex = 0
        while (startIndex < days.indexOf(startDay)) {
            val day = days[startIndex]
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = day.getDisplayName(TextStyle.SHORT, Locale.KOREAN).lowercase(),
                textAlign = TextAlign.Center,
                style = androidx.compose.ui.text.TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 12.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                ),
                color = contentBlack
            )
            startIndex++
        }
    }
}
