package eu.tutorial.moodle.ui.calender

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
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
fun HorizontalCalender(
    modifier: Modifier = Modifier,
    currentDate: LocalDate = LocalDate.now()
){
    val currentMonth = YearMonth.now()
    val startOfMonth = currentMonth.atDay(1)
    val endOfMonth = currentMonth.atEndOfMonth()

    // 현재 달의 모든 날짜 가져오기
    val datesInCurrentMonth = generateSequence(startOfMonth) { it.plusDays(1) }
        .takeWhile { it <= endOfMonth }

    val daysOfWeek = (0 until 7).map {
        currentMonth
            .atDay(it + 1)
            .dayOfWeek
            .getDisplayName(TextStyle.SHORT, Locale.getDefault())
    }

    val firstDayOfWeek = currentMonth.atDay(1).with(DayOfWeek.SUNDAY)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp)
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
    val firstDayOfWeek = YearMonth.now().atDay(1).dayOfWeek.value
    val days = IntRange(1, lastDay).toList()

    Column(modifier = modifier.fillMaxSize()) {
        DayOfWeek()
        LazyVerticalGrid(
            modifier = Modifier.height(260.dp),
            columns = GridCells.Fixed(7)
        ) {
            for (i in 1 until firstDayOfWeek + 1) { // 처음 날짜가 시작하는 요일 전까지 빈 박스 생성
                item {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp)
                    )
                }
            }
            items(days) { day ->
                val date = currentDate.withDayOfMonth(day)
                val isSelected = remember(selectedDate) {
                    selectedDate.compareTo(date) == 0
                }
                CalendarDay(
                    modifier = Modifier.padding(top = 10.dp),
                    date = date,
                    isToday = date == LocalDate.now(),
                    isSelected = isSelected
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isToday: Boolean,
    isSelected: Boolean
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .size(30.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0XFFEFEFEF)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = date.dayOfMonth.toString(),
            style = androidx.compose.ui.text.TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 11.69.sp,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
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
                    fontFamily = FontFamily(Font(R.font.poppins)),
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
                    fontFamily = FontFamily(Font(R.font.poppins)),
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
    HorizontalCalender()
}