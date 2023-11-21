package eu.tutorial.moodle.ui.calendar.component

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
import androidx.compose.runtime.rememberCoroutineScope
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
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray
import eu.tutorial.moodle.ui.theme.currentMonth
import eu.tutorial.moodle.ui.theme.mainOrange
import eu.tutorial.moodle.ui.theme.todayBox
import eu.tutorial.moodle.ui.theme.todayText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMonthItem(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit,
    onClickAction: (String) -> Unit
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

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
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
                    onSelectedDate = onSelectedDate,
                    onClickAction = onClickAction
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
                    onSelectedDate = onSelectedDate,
                    onClickAction = onClickAction
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
                    onSelectedDate = onSelectedDate,
                    onClickAction = onClickAction
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
    onSelectedDate: (LocalDate) -> Unit = {},
    onClickAction: (String) -> Unit = {}
) {
    val boxColor =
        if (isCurrentMonth)
            if (isToday) todayBox else currentMonth
        else
            containerGray

    val textColor =
        if (isCurrentMonth)
            if (isToday) todayText else contentBlack
        else
            contentGray

    val borderColor = if (isSelected) mainOrange else Color.Transparent

    val coroutineScope = rememberCoroutineScope()

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

                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        // 데이터베이스 쿼리를 비동기적으로 수행
                        onClickAction(date.toString())
                        // 결과를 처리하거나 ViewModel에 저장
                    }
                }

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

