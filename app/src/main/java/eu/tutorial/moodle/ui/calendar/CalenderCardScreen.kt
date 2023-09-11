package eu.tutorial.moodle.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorizontalCalendar(
    modifier: Modifier = Modifier,
    currentDate: LocalDate = LocalDate.now()
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 16.dp)
    ) {
        val pageCount = 144
        HorizontalPager(
            pageCount = pageCount,
        ) {
//                page ->
//            val date = LocalDate.of(
//                2011 + page / 12,
//                page % 12 + 1,
//                1
//            )
            CalendarMonthItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                currentDate = currentDate,
                selectedDate = currentDate
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMonthItem(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selectedDate: LocalDate
) {
    val lastDay = currentDate.lengthOfMonth()

    val currentMonth = YearMonth.now()
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek.value

    val days = IntRange(1, lastDay).toList()

    val lastMonthDate = currentMonth.atDay(1).minusDays(1)

    val lastDays = IntRange(lastMonthDate.lengthOfMonth() - firstDayOfMonth + 1, lastMonthDate.lengthOfMonth()).toList()

    val nextDays = IntRange(1, 42 - lastDays.size - days.size).toList()

    Column(modifier = modifier.fillMaxSize()) {
        DayOfWeek()
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(7),
        ) {
            items(lastDays) {day ->
                CalendarDay(
                    modifier = Modifier.padding(top = 10.dp),
                    day = day,
                    isToday = false,
                    isSelected = false,
                    isCurrentMonth = false
                )
            }
            items(days) { day ->
                val date = currentDate.withDayOfMonth(day)
                val isSelected = remember(selectedDate) {
                    selectedDate.compareTo(date) == 0
                }
                CalendarDay(
                    modifier = Modifier.padding(top = 10.dp),
                    day = day,
                    isToday = date == LocalDate.now(),
                    isSelected = isSelected
                )
            }
            items(nextDays) {day ->
                CalendarDay(
                    modifier = Modifier.padding(top = 8.dp),
                    day = day,
                    isToday = false,
                    isSelected = false,
                    isCurrentMonth = false
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    day: Int,
    isToday: Boolean,
    isSelected: Boolean,
    isCurrentMonth: Boolean = true
) {
    val boxColor =
        if(isCurrentMonth)
            if (isToday) Color(0XFF414141) else Color(0XFFEFEFEF)
        else
            Color(0X80EFEFEF)

    val textColor =
        if(isCurrentMonth)
            if (isToday) Color(0XFFFFFFFF) else Color(0XFF000000)
        else
            Color(0X4D000000)
    Box(
        modifier = modifier
            .wrapContentSize()
            .size(38.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                color = boxColor
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = day.toString(),
            style = androidx.compose.ui.text.TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 11.69.sp,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                color = textColor
            ),

        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayOfWeek(
    modifier: Modifier = Modifier
) {
    val startDay = DayOfWeek.SUNDAY

    val days = DayOfWeek.values()
    var startIndex = days.indexOf(startDay)

    Row(modifier) {

        for (i in startIndex until days.size) {
            val day = days[i]
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = day.getDisplayName(TextStyle.NARROW, Locale.ENGLISH).lowercase(),
                textAlign = TextAlign.Center,
                style = androidx.compose.ui.text.TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 20.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }

        startIndex = 0
        while (startIndex < days.indexOf(startDay)) {
            val day = days[startIndex]
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = day.getDisplayName(TextStyle.NARROW, Locale.ENGLISH).lowercase(),
                textAlign = TextAlign.Center,
                style = androidx.compose.ui.text.TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 20.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
            startIndex++
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CalendarPreview(){
    HorizontalCalendar()
}