package eu.tutorial.moodle.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.calendar.component.HorizontalCalendar
import eu.tutorial.moodle.ui.home.HomeViewModel
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.poppins
import eu.tutorial.moodle.ui.view.ViewScreen
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMainCard(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    currentDate: LocalDate = LocalDate.now(),
    visibleMore: Boolean,
    showViewScreen: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    val initialPage = (currentDate.year - 1970) * 12 + currentDate.monthValue - 1

    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var currentPage by remember { mutableStateOf(initialPage) }
    val pagerState = rememberPagerState(initialPage = initialPage)

    var visibleEmotion by remember { mutableStateOf(false) }

    val currentSelectedDate = remember { mutableStateOf(currentDate) }

    LaunchedEffect(pagerState.currentPage) {
        val addMonth = (pagerState.currentPage - currentPage).toLong()
        currentMonth = currentMonth.plusMonths(addMonth)
        currentPage = pagerState.currentPage
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGray)
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 24.dp)
                .clip(RoundedCornerShape(32.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(394.dp)
                    .background(containerGray),
                contentAlignment = Alignment.Center
            ) {
                HorizontalCalendar(
                    pagerState = pagerState,
                    changeVisible = {
                        visibleEmotion = !visibleEmotion
                    },
                    currentMonth = currentMonth,
                    viewModel = viewModel,
                    currentSelectedDate = currentSelectedDate
                )
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                    .clip(RoundedCornerShape(32.dp))
            ) {
                Box(
                    modifier = Modifier
                        .height(226.dp)
                        .fillMaxWidth()
                        .background(containerGray),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = {
                            showViewScreen()
                        },
                        modifier = Modifier
                            .padding(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.journal),
                                contentDescription = "journal",
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(16.dp)
                            )

                            Text(
                                text = "전체 보기",
                                color = contentBlack,
                                fontFamily = poppins,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }
            }
        }

    }


    Box(
        modifier = Modifier
            .background(color = Color(0X00000000))
    ) {
        AnimatedVisibility(
            visible = visibleMore,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
        ) {
            ViewScreen(
                showViewScreen = showViewScreen,
                selectedDate = currentSelectedDate
            ) {}
        }
    }

}