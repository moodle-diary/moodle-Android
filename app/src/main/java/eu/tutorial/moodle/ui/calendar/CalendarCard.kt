package eu.tutorial.moodle.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.component.CalendarMonthItem
import eu.tutorial.moodle.ui.component.DayOfWeek
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
    currentDate: LocalDate = LocalDate.now(),
    currentMonth: YearMonth,
    pagerState: PagerState,
    changeVisible : () -> Unit,
) {

    var currentSelectedDate by remember { mutableStateOf(currentDate) }

    LaunchedEffect(currentSelectedDate) {
        val idx = currentSelectedDate.month.value + 12 * (currentSelectedDate.year - 1970) - 1
        // 개수로 세면 1월이 0 부터 시작인데, 실제는 1

        // TODO : 앞 혹은 뒤로 많이 갔을 때, 현재 날짜로 변경 되면, 그 페이지로 한 번에 돌아 가도록 한다.
        while(idx != pagerState.currentPage){
            if (idx < (pagerState.currentPage)) {
                pagerState.scrollToPage(page = pagerState.currentPage - 1)
            } else {
                pagerState.scrollToPage(page = pagerState.currentPage + 1)
            }
        }

    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 16.dp)
            .padding(horizontal = 20.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "%s %d".format(currentMonth.month, currentMonth.year),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                )
            )

            IconButton(
                onClick = { currentSelectedDate = LocalDate.now() },
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "move current date")
            }
        }

        DayOfWeek()

        // TODO 이 부분 은 연도 시작, 1970 부터 2133 까지 이 부분 config 로 빼기
        val pageCount = (2133 - 1970) * 12
        HorizontalPager(
            pageCount = pageCount,
            state = pagerState
        ) { page ->
            val date = LocalDate.of(
                page / 12 + 1970,
                page % 12 + 1,
                18
            )

            CalendarMonthItem(
                modifier = Modifier
                    .fillMaxWidth(),
                currentDate = date,
                selectedDate = currentSelectedDate,
                onSelectedDate = { selectedDate ->
                    currentSelectedDate = selectedDate
                    changeVisible()
                },
            )
        }
    }
}
