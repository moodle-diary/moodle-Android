package eu.tutorial.moodle.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.ui.component.IconsComponent
import eu.tutorial.moodle.ui.component.NotesComponent
import eu.tutorial.moodle.ui.component.SentenceComponent
import eu.tutorial.moodle.ui.theme.poppins


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailHomeScreen(
    modifier: Modifier = Modifier,
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
//    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController = rememberNavController()
) {
//    val diaryList = viewModel.diaryUiState
//    val causeList = viewModel.causeUiState
//    val placeList = viewModel.placesUiState

    val coroutineScope = rememberCoroutineScope()
    var isBottomSheetVisible by remember { mutableStateOf(false) }

//    LaunchedEffect(Unit){
//        coroutineScope.launch {
//            withContext(Dispatchers.IO) {
//                // 데이터베이스 쿼리를 비동기적으로 수행
//                viewModel.getDiaries(LocalDate.now().toString())
//                viewModel.getCauses(LocalDate.now().toString())
//                viewModel.getPlaces(LocalDate.now().toString())
//            }
//        }
//    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0XFF151515))
            .padding(innerPaddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        // TODO 이 부분 exist 속성 값 vm 에서 받아 와서 수정 Detail HomeScreen 이랑 통합

        // TODO : 이 부분은 명언 보여주기
        SentenceComponent(
//            emotion = getDiaryEmotion(diaryList),
            modifier = Modifier.padding(start = 41.dp, end = 41.dp),
            navController = navController
        )

        Spacer(modifier = Modifier.size(87.dp))



        Column(
            modifier = Modifier.padding(start = 50.dp, end = 50.dp)
        ) {
            Row(
                modifier = modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "오늘의 리마인드",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0XFFDFDFDF)
                )
                Text(
                    text = "모두 보기",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0XFF7E7E7E)
                )
            }

            IconsComponent()

        }

        Spacer(modifier = Modifier.size(32.dp))

        Button(
            onClick = { isBottomSheetVisible = true },
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
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = "생각 보기",
                    color = Color(0XFFDFDFDF),
                    fontFamily = poppins,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        if (isBottomSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = { isBottomSheetVisible = false },
                containerColor = Color(0XFF151515),
                modifier = Modifier
                    .fillMaxHeight(0.85f),  // 야매여서 고쳐야함
                tonalElevation = 0.dp,
                scrimColor = Color.Transparent,
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp),

                    ) {
                    Spacer(modifier = Modifier.size(12.dp))

                    Text(
                        text = "원인 아이콘",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0XFFDFDFDF)
                    )

                    IconsComponent(
                        modifier = Modifier.height(97.dp)
                    )

                    Spacer(modifier = Modifier.size(46.dp))

                    Text(
                        text = "장소 아이콘",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0XFFDFDFDF)
                    )

                    IconsComponent(
                        modifier = Modifier.height(97.dp)
                    )

                    Spacer(modifier = Modifier.size(43.dp))

                    Text(
                        text = "생각",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0XFFDFDFDF)
                    )

                    NotesComponent(
//                        text = getDiaryText(diaryList)
                    )

                    Spacer(modifier = Modifier.size(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.size(32.dp))

    }
}

fun getDiaryEmotion(diaryList: List<DiaryDto>): Int {
    var emotion = 0

    for (i in diaryList)
        emotion += i.emotions

    return emotion
}

fun getDiaryText(diaryList: List<DiaryDto>): String {
    var result = ""

    for (i in diaryList)
        result += i.diaryText

    return result
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PreviewMainPage() {
    DetailHomeScreen()
}