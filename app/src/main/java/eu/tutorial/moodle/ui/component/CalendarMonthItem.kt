package eu.tutorial.moodle.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMonthItem(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit,
) {
    val lastDay = currentDate.lengthOfMonth()
    val days = IntRange(1, lastDay).toList()

    val lastMonthDate = LocalDate.of(
        currentDate.year, currentDate.month, 1
    ).minusDays(1)

    val lastDays = IntRange(
        lastMonthDate.lengthOfMonth() - lastMonthDate.dayOfWeek.value,
        lastMonthDate.lengthOfMonth()
    ).toList()

    val nextMonthDate = LocalDate.of(
        currentDate.year, currentDate.month, lastDay
    ).plusDays(1)

    val nextDays = IntRange(
        1, 42 - lastDays.size - days.size
    ).toList()

    Column(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(7),
        ) {
            items(lastDays) { day ->

                val date = lastMonthDate.withDayOfMonth(day)
                val isSelected = remember(selectedDate) {
                    selectedDate.compareTo(date) == 0
                }

                CalendarDay(
                    modifier = Modifier.padding(top = 10.dp),
                    day = day,
                    date = date,
                    isToday = false,
                    isSelected = isSelected,
                    isCurrentMonth = false,
                    onSelectedDate = onSelectedDate
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
                    date = date,
                    isToday = date == LocalDate.now(),
                    isSelected = isSelected,
                    onSelectedDate = onSelectedDate
                )
            }
            items(nextDays) { day ->

                val date = nextMonthDate.withDayOfMonth(day)
                val isSelected = remember(selectedDate) {
                    selectedDate.compareTo(date) == 0
                }

                CalendarDay(
                    modifier = Modifier.padding(top = 8.dp),
                    day = day,
                    date = date,
                    isToday = false,
                    isSelected = isSelected,
                    isCurrentMonth = false,
                    onSelectedDate = onSelectedDate
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
    date: LocalDate = LocalDate.now(),
    isSelected: Boolean,
    isCurrentMonth: Boolean = true,
    onSelectedDate: (LocalDate) -> Unit = {}
) {
    val boxColor =
        if (isCurrentMonth)
            if (isToday) Color(0XFF414141) else Color(0XFFEFEFEF)
        else
            Color(0X80EFEFEF)

    val textColor =
        if (isCurrentMonth)
            if (isToday) Color(0XFFFFFFFF) else Color(0XFF000000)
        else
            Color(0X4D000000)

    val borderColor = if (isSelected) Color(0XFF414141) else Color(0X00414141)
    Box(
        modifier = modifier
            .wrapContentSize()
            .border(
                width = 3.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 15.dp)
            )
            .size(38.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(
                color = boxColor
            )
            .clickable {
                onSelectedDate(date)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = day.toString(),
            style = TextStyle(
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

