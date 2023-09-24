package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.navigation.HomeDestination
import eu.tutorial.moodle.ui.navigation.NavigationDestination
import java.time.LocalDate




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostEmotionScreen(navController: NavController) {
    val localDate: LocalDate = LocalDate.now()
    val day = localDate.dayOfMonth
    val month = localDate.dayOfWeek

    var showDialog by remember { mutableStateOf(false) }
    var isCancel by remember { mutableStateOf(false) }

    val actualPageCount = 6
    val pageCount = Int.MAX_VALUE
    val maxNumOfRounds = Int.MAX_VALUE / actualPageCount
    val pagerState = rememberPagerState(
        initialPage = (maxNumOfRounds / 2) * actualPageCount
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 28.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight(500),
            text = "$day $month"
        )

        PagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp, start = 9.dp, end = 9.dp),
            count = actualPageCount,
            dotSize = 15.dp,
            currentPage = pagerState.currentPage % actualPageCount,
            selectedColor = Color(0XFFC5C1C1),
            unSelectedColor = Color(0XFFD9D9D9)
        )

        HorizontalPager(
            modifier = Modifier.height(520.dp),
            pageCount = pageCount,
            state = pagerState,
            pageSpacing = 10.dp,
        ) { page ->
            PageCard(
                page = page % actualPageCount,
                modifier = Modifier.clip(shape = CircleShape.copy(all = CornerSize(32.dp)))
            ) {
                when (page % actualPageCount) {
                    0 -> MoodGrid()
                    1, 2 -> ActGrid()
                    3 -> PlaceGrid()
                    4 -> PostGrid()
                    5 -> ImgGrid()
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    showDialog = true
                    isCancel = true },
                modifier = Modifier
                    .width(90.dp)
                    .height(60.dp)
                    .clip(shape = CircleShape.copy(all = CornerSize(32.dp))),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                elevation = ButtonDefaults.buttonElevation( //버튼 그림자 없애기
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )
            ) {
                Text(

                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    text = "Cancel",
                    color = Color(0XFF000000)
                )
            }

            Button(
                onClick = { showDialog = true
                    isCancel = false},
                modifier = Modifier
                    .width(163.dp)
                    .height(60.dp)
                    .clip(shape = CircleShape.copy(all = CornerSize(32.dp))),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD9D9D9) // #d9d9d9에 해당하는 색상으로 변경
                )
            ) {
                Text(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    text = "Save",
                    color = Color(0XFF000000)
                )
            }

            if (showDialog) {
                val dialogText = if (isCancel) {
                    "Do you cancel and leave?\nContent will not be saved"
                } else {
                    "Do you want to save\nand leave for now?"
                }
                Dialog(
                    onDismissRequest = { showDialog = false },
                    properties = DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                ) {
                    Card(
                        shape = RoundedCornerShape(32.dp),
                        modifier = Modifier
                            .width(282.dp)
                            .height(175.dp)
                            .background(Color.White)
                            .clip(shape = CircleShape.copy(all = CornerSize(52.dp)))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = dialogText,
                                modifier = Modifier.padding(top = 29.dp, bottom = 36.dp),
                                fontSize = 16.sp,
                                color = Color(0XFF000000)
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp)
                            ) {
                                Button(
                                    onClick = { showDialog = false },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                    ),
                                    elevation = ButtonDefaults.buttonElevation( //버튼 그림자 없애기
                                        defaultElevation = 0.dp,
                                        pressedElevation = 0.dp
                                    )
                                ) {
                                    Text(
                                        text = "Continue writing",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0XFF000000)
                                    )
                                }
                                Button(
                                    onClick = {
                                        navController.navigate(HomeDestination.route)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFD9D9D9),
                                        contentColor = Color.Black
                                    ),
                                    modifier = Modifier
                                        .width(98.dp)
                                        .height(46.dp)
                                        .clip(shape = CircleShape.copy(all = CornerSize(32.dp)))
                                ) {
                                    Text(
                                        text = "Leave",
                                        fontSize = 16.sp,
                                        color = Color(0XFF000000)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PageCard(page: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(
        modifier = modifier,
        color = Color.DarkGray,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PostEmotionPreview(){
    //PostEmotionScreen(navController = navController)
}