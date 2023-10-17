package eu.tutorial.moodle.ui.chart

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import java.time.LocalDate
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChartTopBar(){
    val today = LocalDate.now()

    val currentMonth = today.monthValue
    val currentDate = today.dayOfMonth
    val currentDay = today.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale.KOREAN)
    val currentYear = today.year

    Box(
        modifier = Modifier
            .height(108.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFF151515))
                .padding(start = 50.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "월간 분석",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFFDFDFDF),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "%s %s월".format(currentYear, currentMonth),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0xFFDFDFDF),
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                    )
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "down",
                    tint = Color(0XFFDFDFDF),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(35.dp)
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TopBarPreview(){
    ChartTopBar()
}