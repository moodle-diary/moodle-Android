package eu.tutorial.moodle.ui.post

import android.net.Uri
import android.os.Build
import android.util.Log
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
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostEmotionScreen(
    navController: NavController,
    viewModel: PostViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val localDate: LocalDate = LocalDate.now()
    val day = localDate.dayOfMonth
    val month = localDate.dayOfWeek

    var showDialog by remember { mutableStateOf(false) }
    var isCancel by remember { mutableStateOf(false) }

    val actualPageCount = 7
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
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 28.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight(500),
            text = "$day $month",
//            platformStyle = PlatformTextStyle(
//                includeFontPadding = false
//            )
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

        val actButtonStates = remember {
            mutableStateListOf<Boolean>()
        }

        val placeButtonStates = remember {
            mutableStateListOf<Boolean>()
        }

        val peopleButtonStates = remember {
            mutableStateListOf<Boolean>()
        }

        val foodButtonStates = remember {
            mutableStateListOf<Boolean>()
        }

        val imageUri = remember {
            mutableStateOf<Uri?>(null)
        }

        HorizontalPager(
            modifier = Modifier.height(520.dp),
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

                    3 -> PeopleGrid(
                        peopleButtonStates = peopleButtonStates
                    )

                    4 -> FoodGrid(
                        foodButtonStates = foodButtonStates
                    )

                    5 -> PostGrid(
                        diaryUiState = viewModel.diaryUiState,
                        valueChange = viewModel::updateDiaryUiState
                    )

                    6 -> ImgGrid(
                        imgUri = imageUri
                    )
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
                    .width(100.dp)
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
                onClick = {
                    showDialog = true
                    isCancel = false
                          },
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
                            .background(Color.Transparent)
                            .clip(shape = CircleShape.copy(all = CornerSize(32.dp)))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
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

                                            for (i in 0 until peopleButtonStates.size)
                                                if(peopleButtonStates[i]) viewModel.savePeople(
                                                    peopleData[i], key
                                                )

                                            for(i in 0 until foodButtonStates.size)
                                                if(foodButtonStates[i]) viewModel.saveFood(
                                                    foodData[i], key
                                                )

                                            viewModel.saveImg(
                                                imageUri.value, key
                                            )
                                        }
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