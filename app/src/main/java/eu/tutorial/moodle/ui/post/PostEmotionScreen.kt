package eu.tutorial.moodle.ui.post

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.activitiesData
import eu.tutorial.moodle.data.local.foodData
import eu.tutorial.moodle.data.local.peopleData
import eu.tutorial.moodle.data.local.placesData
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.navigation.HomeDestination
import eu.tutorial.moodle.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostEmotionScreen(
    navController: NavController,
    viewModel: PostViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val localDate: LocalDate = LocalDate.now()
    val day = localDate.dayOfMonth
    val month = localDate.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale.KOREAN)

    var showDialog by remember { mutableStateOf(false) }
    var isCancel by remember { mutableStateOf(false) }

    val actualPageCount = 5
    val initialPage = 0
    val pagerState = rememberPagerState(
        initialPage = initialPage,
    )

    val coroutineScope = rememberCoroutineScope()

    val emotionButtonStates = remember {
        mutableStateListOf(
            mutableStateListOf(false, false, false, false),
            mutableStateListOf(false, false, false, false),
            mutableStateListOf(false, false, false, false),
            mutableStateListOf(false, false, false, false)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF151515))
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .height(53.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Transparent)
            )
            Text(
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = Color(0XFFDFDFDF),
                text = "$day $month",
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        showDialog = true
                        isCancel = true
                    },
                tint = Color(0XFFDFDFDF)
            )
        }

        PagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp, start = 9.dp, end = 9.dp),
            count = actualPageCount,
            dotSize = 15.dp,
            currentPage = pagerState.currentPage % actualPageCount,
            selectedColor = Color(0XFF686868),
            unSelectedColor = Color(0XFF686868)
        )

        val actButtonStates = remember {
            mutableStateListOf<Boolean>()
        }

        val placeButtonStates = remember {
            mutableStateListOf<Boolean>()
        }



        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Transparent)
        ) {
            HorizontalPager(
                modifier = Modifier
                    .weight(1f),
                pageCount = actualPageCount, // pageCount를 변경한 값으로 설정
                state = pagerState,
                pageSpacing = 10.dp,
            ) { page ->
                val actualPage = page % actualPageCount // 실제 페이지를 계산합니다

                PageCard(
                    page = actualPage,
                    modifier = Modifier.clip(shape = CircleShape.copy(all = CornerSize(32.dp)))
                ) {
                    when (actualPage) {
                        0 -> MoodGrid(
                            buttonStates = emotionButtonStates,
                            diaryUiState = viewModel.diaryUiState,
                            onClick = viewModel::updateDiaryUiState,
                        )

                        1 -> ActGrid(
                            actButtonStates = actButtonStates
                        )

                        2 -> PlaceGrid(
                            placeButtonStates = placeButtonStates
                        )

                        3 -> PostGrid(
                            diaryUiState = viewModel.diaryUiState,
                            valueChange = viewModel::updateDiaryUiState
                        )

                    }
                }
            }

            Spacer(modifier = Modifier.height(21.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        showDialog = true
                        isCancel = false
                    },
                    modifier = Modifier
                        .width(163.dp)
                        .height(60.dp)
                        .clip(shape = CircleShape.copy(all = CornerSize(32.dp))),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF363637)
                    )
                ) {
                    Text(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        text = "저장하기",
                        color = Color(0XFFEDEDED),
                    )
                }

                if (showDialog) {
                    val dialogText = if (isCancel) {
                        "저장하지 않고 나가겠어요?"
                    } else {
                        "지금까지의 내용을 저장하시겠어요?"
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
                                .background(Color.Transparent)
                                .clip(shape = CircleShape.copy(all = CornerSize(32.dp)))
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0XFF212122))
                            ) {
                                Text(
                                    text = dialogText,
                                    modifier = Modifier.padding(top = 38.dp, bottom = 36.dp),
                                    fontSize = 16.sp,
                                    color = Color(0XFFDFDFDF),
                                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp, end = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Button(
                                        onClick = { showDialog = false },
                                        modifier = Modifier
                                            .defaultMinSize(
                                                minWidth = 131.dp,
                                                minHeight = 46.dp
                                            )
                                            .clip(shape = CircleShape.copy(all = CornerSize(32.dp))),
                                        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp, start = 26.dp, end = 26.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Transparent
                                        ),
                                        elevation = ButtonDefaults.buttonElevation( //버튼 그림자 없애기
                                            defaultElevation = 0.dp,
                                            pressedElevation = 0.dp
                                        )
                                    ) {
                                        Text(
                                            text = if (isCancel) {
                                                "계속 작성하기"
                                            } else {
                                                "조금 더 작성하기"
                                            },
                                            fontSize = 12.sp,
                                            color = Color(0XFF888888),
                                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                                        )
                                    }
                                    Button(
                                        onClick = {
                                            //TODO : Transaction 고려
                                            coroutineScope.launch {
                                                navController.navigate(HomeDestination.route)

                                                val key = viewModel.saveDiary()

                                                for (i in 0 until actButtonStates.size)
                                                    if(actButtonStates[i]) viewModel.saveActivity(
                                                        activitiesData[i], key
                                                    )

                                                for (i in 0 until placeButtonStates.size)
                                                    if(placeButtonStates[i]) viewModel.savePlace(
                                                        placesData[i], key
                                                    )
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFD9D9D9),
                                            contentColor = Color(0XFF151515)
                                        ),
                                        modifier = Modifier
                                            .defaultMinSize(
                                                minWidth = 98.dp,
                                                minHeight = 46.dp
                                            )
                                            .clip(shape = CircleShape.copy(all = CornerSize(32.dp))),
                                        contentPadding = PaddingValues(top = 13.dp, bottom = 13.dp, start = 21.dp, end = 21.dp),
                                    ) {
                                        Text(
                                            text = if (isCancel) {
                                                    "나가기"
                                                } else {
                                                    "저장하기"
                                            },
                                            fontSize = 14.sp,
                                            color = Color(0XFF151515),
                                            fontFamily = FontFamily(Font(R.font.poppins_bold))
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PostEmotionScreenPreview() {
    val navController = rememberNavController()
    PostEmotionScreen(navController)
}