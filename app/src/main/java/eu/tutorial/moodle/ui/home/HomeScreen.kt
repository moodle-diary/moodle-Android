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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.component.IconsComponent
import eu.tutorial.moodle.ui.component.SentenceComponent
import eu.tutorial.moodle.ui.home.component.BottomSheet
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.poppins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController = rememberNavController()
) {
    val emotionList = viewModel.emotionUiState
    val coroutineScope = rememberCoroutineScope()
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getEmotions(LocalDate.now().toString())
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundGray)
            .padding(innerPaddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        // TODO : 이 부분은 명언 보여주기
        SentenceComponent(
            modifier = Modifier.padding(20.dp, 10.dp),
            navController = navController
        )

        Spacer(modifier = Modifier.size(30.dp))

        Column(
            modifier = Modifier.padding(12.dp, 0.dp)
        ) {
            Row(
                modifier = modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "오늘 리마인드",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = contentBlack,
                    modifier = Modifier.padding(8.dp, 0.dp)
                )
            }

            IconsComponent(
                iconList = emotionList,
            )
        }
        Spacer(modifier = Modifier.size(20.dp))

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
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(16.dp)
                )

                Text(
                    text = "생각 보기",
                    color = contentBlack,
                    fontFamily = poppins,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        BottomSheet(
            visibility = isBottomSheetVisible,
            viewModel = viewModel
        ) { visibility ->
            isBottomSheetVisible = visibility
        }

        Spacer(modifier = Modifier.size(32.dp))
    }
}

fun getDiaryText(diaryList: List<DiaryDto>): List<String> {
    return diaryList.map { it.diaryText }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PreviewMainPage() {
    HomeScreen()
}
