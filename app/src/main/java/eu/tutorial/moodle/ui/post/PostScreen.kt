package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.post.component.PostGrid
import eu.tutorial.moodle.ui.post.component.SaveDialog
import eu.tutorial.moodle.ui.post.component.TimeDialog
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.mainOrange
import eu.tutorial.moodle.ui.theme.poppins
import eu.tutorial.moodle.ui.theme.subYellow
import eu.tutorial.moodle.ui.theme.unselectedIndicator
import java.time.LocalDate
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostScreen(
    navController: NavController,
    viewModel: PostViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val selectedHour = viewModel.diaryUiState.diaryDetails.hour
    val selectedMinute = viewModel.diaryUiState.diaryDetails.minute
    var showDialog by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )

    // TODO stateList 들 viewModel 로 이전
    val localDate: LocalDate = LocalDate.now()
    val day = localDate.dayOfMonth
    val month = localDate.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale.KOREAN)

    val actualPageCount = 4
    val initialPage = 0
    val pagerState = rememberPagerState(
        initialPage = initialPage,
    )

    val emotionButtonStates = remember {
        mutableStateListOf(
            mutableStateListOf(false, false, false, false),
            mutableStateListOf(false, false, false, false),
            mutableStateListOf(false, false, false, false),
            mutableStateListOf(false, false, false, false)
        )
    }

    val causeButtonStates = remember {
        mutableStateListOf<Boolean>()
    }

    val placeButtonStates = remember {
        mutableStateListOf<Boolean>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGray)
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
                color = contentBlack,
                text = "$day $month",
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        viewModel.showDialog = true
                        viewModel.isCancel = true
                    },
                tint = contentBlack
            )
        }

        PagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(60.dp, 24.dp),
            count = actualPageCount,
            dotSize = 15.dp,
            currentPage = pagerState.currentPage % actualPageCount,
            selectedColor = mainOrange,
            unSelectedColor = unselectedIndicator
        )


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
                            navController = navController,
                            viewModel = viewModel
                        )

                        1 -> CauseGrid(
                            causeButtonStates = causeButtonStates,
                            viewModel = viewModel,
                        )

                        2 -> PlaceGrid(
                            placeButtonStates = placeButtonStates,
                            viewModel = viewModel,
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
                    .padding(20.dp, 0.dp)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .width(50.dp)
                        .height(60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "clock",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(bottom = 3.dp)
                            .clickable { showDialog = true }
                    )
                    Text(
                        text = "$selectedHour : $selectedMinute",
                        fontSize = 11.sp,
                        fontFamily = poppins,
                        color = contentBlack.copy(alpha = 0.6f)
                    )
                }
                Button(
                    onClick = {
                        viewModel.showDialog = true
                        viewModel.isCancel = false
                    },
                    modifier = Modifier
                        .width(163.dp)
                        .height(60.dp)
                        .clip(shape = CircleShape.copy(all = CornerSize(32.dp))),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = containerGray
                    )
                ) {
                    Text(
                        text = "저장하기",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = contentBlack,
                    )
                }
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .background(Color.Transparent)
                )

                if (viewModel.showDialog) {
                    SaveDialog(
                        emotionButtonStates = emotionButtonStates,
                        causeButtonStates = causeButtonStates,
                        placeButtonStates = placeButtonStates,
                        navController = navController,
                        viewModel = viewModel,
                        isCancel = viewModel.isCancel,
                    ) { dialogVisible -> viewModel.showDialog = dialogVisible }
                }

                TimeDialog(
                    showDialog = showDialog,
                    onDismissRequest = { showDialog = false },
                    timePickerState = timePickerState
                ) { hour, minute ->
                    showDialog = false
                    viewModel.updateDiaryUiState(
                        diaryDetails = viewModel.diaryUiState.diaryDetails.copy(
                            hour = hour,
                            minute = minute
                        )
                    )
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
    //PostScreen(navController)
}