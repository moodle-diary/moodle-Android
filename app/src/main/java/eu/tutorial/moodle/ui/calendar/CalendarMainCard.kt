package eu.tutorial.moodle.ui.calendar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.tutorial.moodle.data.local.comments
import eu.tutorial.moodle.ui.comment.CommentScreen
import eu.tutorial.moodle.ui.component.BottomNavBar
import eu.tutorial.moodle.ui.home.DetailHomeScreen
import eu.tutorial.moodle.ui.theme.poppins
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMainCard(
    innerPadding : PaddingValues = PaddingValues(0.dp),
    currentDate: LocalDate = LocalDate.now(),
    visibleMore : Boolean,
    changeVisibleMore: () -> Unit,
    showCommentScreen: Boolean,
    setShowCommentScreen: (Boolean) -> Unit,
    onCloseClick: () -> Unit
){

    val initialPage = (currentDate.year - 1970) * 12 + currentDate.monthValue - 1

    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var currentPage by remember { mutableStateOf(initialPage) }
    val pagerState = rememberPagerState(initialPage = initialPage)

    var visibleEmotion by remember { mutableStateOf(false) }

    LaunchedEffect(pagerState.currentPage) {
        val addMonth = (pagerState.currentPage - currentPage).toLong()
        currentMonth = currentMonth.plusMonths(addMonth)
        currentPage = pagerState.currentPage
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {

        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 24.dp),
            shape = RoundedCornerShape(32.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(394.dp)
                    .background(Color(0XFFD9D9D9)),
                contentAlignment = Alignment.Center
            ) {
                HorizontalCalendar(
                    pagerState = pagerState,
                    changeVisible = {
                        visibleEmotion = !visibleEmotion
                        Log.d("visible", visibleEmotion.toString())
                    },
                    currentMonth = currentMonth
                )
            }
        }

        Spacer(modifier = Modifier.size(12.dp))

        AnimatedVisibility(
            visible = visibleEmotion,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                    shape = RoundedCornerShape(32.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .height(226.dp)
                            .fillMaxWidth()
                            .background(color = Color(0XEFEFEFEF)),
                        contentAlignment = Alignment.Center
                    ){
                        Button(
                            onClick = {
                                changeVisibleMore()
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0XFFD9D9D9)
                            ),
                        ) {
                            Text(
                                text = "more",
                                color = Color.Black,
                                fontFamily = poppins
                            )
                        }
                    }
                }
                Button(
                    onClick = { setShowCommentScreen(true) },
                    modifier = Modifier
                        .defaultMinSize(
                            minWidth = 1.dp,
                            minHeight = 31.dp
                        )
                        .padding(bottom = 27.dp),
                    contentPadding = PaddingValues(top = 5.dp, bottom = 5.dp, start = 17.dp, end = 17.dp),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        text = "%d Comments".format(1),
                        color = Color.Black,
                        fontFamily = poppins
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = !visibleEmotion,
        ) {
            Card(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                shape = RoundedCornerShape(32.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(247.dp)
                        .background(Color(0XFFEFEFEF)),
                    contentAlignment = Alignment.Center
                ) {
                    EmotionChart()
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .background(color = Color(0X00000000))
    ){
        AnimatedVisibility(
            visible = visibleMore,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
//            modifier = Modifier
//                .align(Alignment.BottomCenter) // 이 align 은 box scope 이기 때문에 안에서 써야 한다.
        ) {
            DetailHomeScreen(
                modifier = Modifier
//                    .clip(shape = RoundedCornerShape(32.dp)) // 이게 먼저 와야함
                    .background(color = Color(0XFF9D9D9D)),
            )
        }
    }

    Box(
        modifier = Modifier
            .background(color = Color(0X00000000))
    ){
        AnimatedVisibility(
            visible = showCommentScreen,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
//            modifier = Modifier
//                .align(Alignment.BottomCenter) // 이 align 은 box scope 이기 때문에 안에서 써야 한다.
        ) {
            CommentScreen(
                modifier = Modifier
//                    .clip(shape = RoundedCornerShape(32.dp)) // 이게 먼저 와야함
                    .background(color = Color.Black.copy(alpha = 0.3f)),
                onCloseClick = onCloseClick,
                comments = comments
            )
        }
    }

}